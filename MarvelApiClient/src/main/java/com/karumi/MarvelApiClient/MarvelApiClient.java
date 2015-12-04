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

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.io.IOException;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Api client for access to Marvel Api.
 */
public class MarvelApiClient {

  private static final int INVALID_AUTH_CODE = 401;
  private static MarvelApiClient singleton;
  private final String publicKey;
  private final String privateKey;
  private final boolean debug;
  private final ApiCall apiCall;

  MarvelApiClient(String publicKey, String privateKey, ApiCall apiCall, boolean debug) {
    this.publicKey = publicKey;
    this.privateKey = privateKey;
    this.apiCall = apiCall;
    this.debug = debug;
  }

  public static MarvelApiClient with(String publicKey, String privateKey) {
    if (singleton == null) {
      singleton = new Builder(publicKey, privateKey).build();
    }
    return singleton;
  }

  <T> T getApi(Class<T> apiRest) {
    return apiCall.obtainApi(apiRest);
  }

  public <T> T execute(Call<T> call) throws MarvelApiException {
    Response<T> response = null;
    try {
      response = call.execute();
    } catch (IOException e) {
      throw new MarvelApiException("Network error", e);
    }
    if (response.isSuccess()) {
      return response.body();
    } else {
      parseError(response);
      return null;
    }
  }

  private void parseError(Response execute) throws MarvelApiException {
    String marvelCode = "";
    String marvelDescription = "";
    if (execute.errorBody() != null) {
      Gson gson = new Gson();
      try {
        String errorBody = execute.errorBody().string();
        MarvelError marvelError = gson.fromJson(errorBody, MarvelError.class);
        marvelCode = marvelError.getCode();
        marvelDescription = marvelError.getMessage();
        if (marvelDescription == null || "".equals(marvelDescription)) {
          marvelDescription = marvelError.getStatus();
        }
      } catch (IOException e) {
      }
    }

    if(execute.code() == INVALID_AUTH_CODE) {
      throw new MarvelAuthApiException(execute.code(), marvelCode, marvelDescription, null);
    } else {
      throw new MarvelApiException(execute.code(), marvelCode, marvelDescription, null);
    }
  }

  /**
   * Fluent API for creating {@link MarvelApiClient} instances.
   */
  @SuppressWarnings("UnusedDeclaration") public static class Builder {

    private static final String MARVEL_API_BASE_URL = "http://gateway.marvel.com/v1/public/";
    private final String privateKey;
    private final String publicKey;
    private boolean debug;
    private Retrofit retrofit;
    private String baseUrl = MARVEL_API_BASE_URL;
    private TimeProvider timeProvider = new TimeProvider();

    public Builder(String publicKey, String privateKey) {
      if (publicKey == null) {
        throw new IllegalArgumentException("publicKey must not be null.");
      }

      if (privateKey == null) {
        throw new IllegalArgumentException("privateKey must not be null.");
      }

      this.publicKey = publicKey;
      this.privateKey = privateKey;
    }

    public Builder debug() {
      this.debug = true;
      return this;
    }

    public Builder baseUrl(String url) {
      this.baseUrl = url;
      return this;
    }

    public Builder retrofit(Retrofit retrofit) {
      if (retrofit == null) {
        throw new IllegalArgumentException("retrofit must not be null.");
      }
      this.retrofit = retrofit;
      return this;
    }

    public MarvelApiClient build() {
      if (retrofit == null) {
        retrofit = createDefaultRetrofit(baseUrl, debug);
      }

      addKeysToParams(retrofit, publicKey, privateKey, timeProvider);

      return new MarvelApiClient(publicKey, privateKey, new ApiCall(retrofit), debug);
    }

    private void addKeysToParams(Retrofit retrofit, String publicKey, String privateKey,
        TimeProvider timeProvider) {
      OkHttpClient client = retrofit.client();
      client.interceptors().add(new AuthInterceptor(publicKey, privateKey, timeProvider));
    }

    private Retrofit createDefaultRetrofit(String baseUrl, boolean debug) {
      OkHttpClient client = new OkHttpClient();
      if (debug) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);
      }

      Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
          .client(client)
          .addConverterFactory(GsonConverterFactory.create())
          .build();

      return retrofit;
    }
  }
}
