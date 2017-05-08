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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Api client for access to Marvel Api.
 */
public class MarvelApiConfig {

  private static MarvelApiConfig singleton;
  private final String publicKey;
  private final String privateKey;
  private final boolean debug;
  private final Retrofit retrofit;

  MarvelApiConfig(String publicKey, String privateKey, Retrofit retrofit, boolean debug) {
    this.publicKey = publicKey;
    this.privateKey = privateKey;
    this.retrofit = retrofit;
    this.debug = debug;
  }

  public static MarvelApiConfig with(String publicKey, String privateKey) {
    if (singleton == null) {
      singleton = new Builder(publicKey, privateKey).build();
    }
    return singleton;
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }

  /**
   * Fluent API for creating {@link MarvelApiConfig} instances.
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

    public MarvelApiConfig build() {
      if (retrofit == null) {
        retrofit = buildRetrofit();
      }

      return new MarvelApiConfig(publicKey, privateKey, retrofit, debug);
    }

    private Retrofit buildRetrofit() {
      OkHttpClient.Builder builder = new OkHttpClient.Builder()
        .addInterceptor(new AuthInterceptor(publicKey, privateKey, timeProvider));
      if (debug) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
      }

      OkHttpClient client = builder.build();
      
      return new Retrofit.Builder().baseUrl(baseUrl)
          .client(client)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
  }
}
