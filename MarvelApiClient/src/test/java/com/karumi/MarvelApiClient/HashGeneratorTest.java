package com.karumi.marvelapiclient;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashGeneratorTest {

  public static final String ANY_TIMESTAMP = "1";
  public static final String ANY_PUBLIC_KEY = "1234";
  public static final String ANY_PRIVATE_KEY = "abcd";
  public static final String VALID_MD5 = "ffd275c5130566a2916217b101f26150";

  @Test public void shouldProvideAValidMd5WhenGiveValidValues() throws Exception {
    HashGenerator hashGenerator = new HashGenerator();

    String md5 = hashGenerator.generateHash(ANY_TIMESTAMP, ANY_PUBLIC_KEY, ANY_PRIVATE_KEY);

    assertEquals(VALID_MD5, md5);
  }
}