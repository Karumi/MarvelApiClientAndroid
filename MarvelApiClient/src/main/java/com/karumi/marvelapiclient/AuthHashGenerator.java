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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class AuthHashGenerator {
  String generateHash(String timestamp, String publicKey, String privateKey)
      throws MarvelApiException {
    try {
      String value = timestamp + privateKey + publicKey;
      MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
      byte[] md5Bytes = md5Encoder.digest(value.getBytes());

      StringBuilder md5 = new StringBuilder();
      for (int i = 0; i < md5Bytes.length; ++i) {
        md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
      }
      return md5.toString();
    }catch (NoSuchAlgorithmException e) {
      throw new MarvelApiException("cannot generate the api key", e);
    }
  }
}
