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

import com.karumi.marvelapiclient.model.CharactersDto;
import com.karumi.marvelapiclient.model.CharactersQuery;
import com.karumi.marvelapiclient.model.MarvelResponse;
import java.io.IOException;
import java.util.Map;

/**
 * This api give access to marvel Characters. You need a valid {@link MarvelApiClient}
 */
public class CharacterApiClient {
  private final MarvelApiClient marvelApiClient;

  public CharacterApiClient(MarvelApiClient marvelApiClient) {
    this.marvelApiClient = marvelApiClient;
  }

  public MarvelResponse<CharactersDto> getAll(int offset, int limit) throws IOException {
    CharacterApiRest api = marvelApiClient.getApi(CharacterApiRest.class);

    CharactersQuery query =
        CharactersQuery.Builder.create().withOffset(offset).withLimit(limit).build();
    return getAll(query);
  }

  public MarvelResponse<CharactersDto> getAll(CharactersQuery charactersQuery) throws IOException {
    CharacterApiRest api = marvelApiClient.getApi(CharacterApiRest.class);

    Map<String, Object> queryAsMap = charactersQuery.getAsMap();
    return api.getCharacters(queryAsMap).execute().body();
  }
}
