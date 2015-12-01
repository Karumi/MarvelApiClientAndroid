package com.karumi.marvelapiclient;

import org.junit.Test;

public class MarvelApiClientTest {

  private static final String ANY_PRIVATE_KEY = "PRIVATEKEY";
  private static final String ANY_PUBLIC_KEY = "PUBLICKEY";

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowInvalidArgumentExceptionWhenCreateWithoutPublicKey() {
    MarvelApiClient.with(null, ANY_PRIVATE_KEY);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowInvalidArgumentExceptionWhenCreateWithoutPrivateKey() {
    MarvelApiClient.with(ANY_PUBLIC_KEY, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowInvalidArgumentExceptionWhenCreateWithInvalidRetrofit() {
    new MarvelApiClient.Builder(ANY_PUBLIC_KEY, ANY_PRIVATE_KEY).retrofit(null);
  }
}