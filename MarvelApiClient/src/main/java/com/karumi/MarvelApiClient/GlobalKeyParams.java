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

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

class GlobalKeyParams implements Interceptor {
  private static final String TIMESTAMP_KEY = "ts";
  private static final String HASH_KEY = "hash";
  private static final String APIKEY_KEY = "apikey";

  private final String publicKey;
  private final String privateKey;
  private final TimeProvider timeProvider;
  private final HashGenerator hashGenerator = new HashGenerator();

  public GlobalKeyParams(String publicKey, String privateKey, TimeProvider timeProvider) {
    this.publicKey = publicKey;
    this.privateKey = privateKey;
    this.timeProvider = timeProvider;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    String timestamp = String.valueOf(timeProvider.currentTimeMillis());
    String hash = null;
    try {
      hash = hashGenerator.generateHash(timestamp, publicKey, privateKey);
    } catch (NoSuchAlgorithmException e) {
      throw new MarvelApiException("cannot generate the api key", e);
    }
    Request request = chain.request();
    HttpUrl url = request.httpUrl()
        .newBuilder()
        .addQueryParameter(TIMESTAMP_KEY, timestamp)
        .addQueryParameter(APIKEY_KEY, publicKey)
        .addQueryParameter(HASH_KEY, hash)
        .build();
    request = request.newBuilder().url(url).build();
    return chain.proceed(request);
  }
}
