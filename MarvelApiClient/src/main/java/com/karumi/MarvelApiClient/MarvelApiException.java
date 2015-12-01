package com.karumi.marvelapiclient;

import java.security.NoSuchAlgorithmException;

public class MarvelApiException extends RuntimeException {
  public MarvelApiException(String description, NoSuchAlgorithmException cause) {
    super(description, cause);
  }
}
