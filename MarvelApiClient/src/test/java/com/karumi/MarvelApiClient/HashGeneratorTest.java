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