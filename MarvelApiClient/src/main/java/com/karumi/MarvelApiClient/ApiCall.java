package com.karumi.marvelapiclient;

import retrofit.Retrofit;

class ApiCall {
  private final Retrofit retrofit;

  public ApiCall(Retrofit retrofit) {
    this.retrofit = retrofit;
  }

  public <T> T obtainApi(Class<T> apiRest) {
    return retrofit.create(apiRest);
  }
}
