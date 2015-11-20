/*
 *
 *  Copyright (C) 2015 Karumi.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.karumi.MarvelApiClient;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Api client for access to Marvel Api.
 */
public class MarvelApiClient {

    private static MarvelApiClient singleton;
    private final String publicKey;
    private final String privateKey;
    private final boolean debug;
    private final Retrofit retrofit;

    MarvelApiClient(String publicKey, String privateKey, Retrofit retrofit, boolean debug) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.retrofit = retrofit;
        this.debug = debug;
    }

    public static MarvelApiClient with(String publicKey, String privateKey) {
        if (singleton == null) {
            synchronized (MarvelApiClient.class) {
                if (singleton == null) {
                    singleton = new Builder(publicKey, privateKey).build();
                }
            }
        }
        return singleton;
    }

    /**
     * Fluent API for creating {@link MarvelApiClient} instances.
     */
    @SuppressWarnings("UnusedDeclaration")
    public static class Builder {

        private static final String MARVEL_URL = "http://gateway.marvel.com/v1/public/";
        private final String privateKey;
        private final String publicKey;
        private boolean debug;
        private Retrofit retrofit;
        private String baseUrl = MARVEL_URL;


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

        public Builder debug(boolean debug) {
            this.debug = debug;
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

            return new MarvelApiClient(publicKey, privateKey, retrofit, debug);
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
