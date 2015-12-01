package com.karumi.marvelapiclient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashGenerator {
  public String generateHash(String timestamp, String publicKey, String privateKey)
      throws NoSuchAlgorithmException {
    String value = timestamp + privateKey + publicKey;
    MessageDigest mdEnc = MessageDigest.getInstance("MD5");
    byte[] md5Bytes = mdEnc.digest(value.getBytes());

    StringBuffer md5 = new StringBuffer();
    for (int i = 0; i < md5Bytes.length; ++i) {
      md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
    }
    return md5.toString();
  }
}
