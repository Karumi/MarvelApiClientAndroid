/*
 *   Copyright (C) 2015 Karumi.
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.karumi.marvelapiclient;

import com.karumi.marvelapiclient.extensions.FileExtensions;
import com.karumi.marvelapiclient.model.MarvelResponse;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import java.io.IOException;
import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class ApiClientTest {
  protected static final String ANY_TIME_ZONE = "PST";
  private static final int OK_CODE = 200;

  private MockWebServer server;

  @Before public void setUp() throws Exception {
    System.setProperty("user.timezone", ANY_TIME_ZONE);
    MockitoAnnotations.initMocks(this);
    this.server = new MockWebServer();
    this.server.start();
  }

  @After public void tearDown() throws Exception {
    server.shutdown();
  }

  protected void enqueueMockResponse() throws IOException {
    enqueueMockResponse(OK_CODE);
  }

  protected void enqueueMockResponse(int code) throws IOException {
    enqueueMockResponse(code, "{}");
  }

  protected void enqueueMockResponse(int code, String response) throws IOException {
    MockResponse mockResponse = new MockResponse();
    mockResponse.setResponseCode(code);
    mockResponse.setBody(response);
    server.enqueue(mockResponse);
  }

  protected void enqueueMockResponse(String fileName) throws IOException {
    String body = FileExtensions.getStringFromFile(getClass(), fileName);
    MockResponse response = new MockResponse();
    response.setResponseCode(OK_CODE);
    response.setBody(body);
    server.enqueue(response);
  }

  protected void assertRequestSentTo(String url) throws InterruptedException {
    RecordedRequest request = server.takeRequest();
    assertEquals(url, request.getPath());
  }

  protected void assertRequestSentToContains(String... paths) throws InterruptedException {
    RecordedRequest request = server.takeRequest();

    for (String path : paths) {
      Assert.assertThat(request.getPath(), StringContains.containsString(path));
    }
  }

  protected <T> void assertBasicMarvelResponse(MarvelResponse<T> marvelResponse) {
    assertEquals(200, marvelResponse.getCode());
    assertEquals("Ok", marvelResponse.getStatus());
    assertEquals("© 2015 MARVEL", marvelResponse.getCopyright());
    assertEquals("Data provided by Marvel. © 2015 MARVEL", marvelResponse.getAttributionText());
    assertEquals("<a href=\"http://marvel.com\">Data provided by Marvel. © 2015 MARVEL</a>",
        marvelResponse.getGetAttributionHtml());
    assertEquals("55122b303dfbc9fdfd12c080eded740b83354269", marvelResponse.getEtag());
  }

  protected String getBaseEndpoint() {
    return server.getUrl("/").toString();
  }
}
