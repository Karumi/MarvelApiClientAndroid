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

import com.karumi.marvelapiclient.model.MarvelResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class ApiClientTest {
  private static final String FILE_ENCODING = "UTF-8";
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
    enqueueMockResponse(OK_CODE, "{}");
  }

  protected void enqueueMockResponse(int code, String response) throws IOException {
    MockResponse mockResponse = new MockResponse();
    mockResponse.setResponseCode(code);
    mockResponse.setBody(response);
    server.enqueue(mockResponse);
  }

  protected void enqueueMockResponse(String fileName) throws IOException {
    MockResponse mockResponse = new MockResponse();
    String fileContent = getContentFromFile(fileName);
    mockResponse.setBody(fileContent);
    server.enqueue(mockResponse);
  }

  protected void assertRequestSentTo(String url) throws InterruptedException {
    RecordedRequest request = server.takeRequest();
    assertEquals(url, request.getPath());
  }

  protected void assertRequestSentToContains(String... paths) throws InterruptedException {
    RecordedRequest request = server.takeRequest();

    for (String path : paths) {
      Assert.assertThat(request.getPath(), containsString(path));
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

  private String getContentFromFile(String fileName) throws IOException {
    fileName = getClass().getResource("/" + fileName).getFile();
    File file = new File(fileName);
    List<String> lines = FileUtils.readLines(file, FILE_ENCODING);
    StringBuilder stringBuilder = new StringBuilder();
    for (String line : lines) {
      stringBuilder.append(line);
    }
    return stringBuilder.toString();
  }
}
