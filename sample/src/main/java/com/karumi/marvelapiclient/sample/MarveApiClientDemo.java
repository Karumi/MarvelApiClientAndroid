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

package com.karumi.marvelapiclient.sample;

import com.karumi.marvelapiclient.CharacterApiClient;
import com.karumi.marvelapiclient.MarvelApiClient;
import com.karumi.marvelapiclient.model.CharactersDto;
import com.karumi.marvelapiclient.model.CharactersQuery;
import com.karumi.marvelapiclient.model.MarvelResponse;

public class MarveApiClientDemo {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("MarveApiClientDemo [publicKey] [privateKey]");
    }
    String publicKey = args[0];
    String privateKey = args[1];

    //MarvelApiClient marvelApiClient = MarvelApiClient.with(publicKey, privateKey);
    MarvelApiClient marvelApiClient =
        new MarvelApiClient.Builder(publicKey, privateKey).debug().build();
    CharacterApiClient characterApiClient = new CharacterApiClient(marvelApiClient);
    CharactersQuery spider = CharactersQuery.Builder.create().withOffset(0).withLimit(10).build();
    MarvelResponse<CharactersDto> all = characterApiClient.getAll(spider);
    System.out.println(all.toString());
  }
}
