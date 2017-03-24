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

import com.karumi.marvelapiclient.model.CharacterDto;
import com.karumi.marvelapiclient.model.CharactersDto;
import com.karumi.marvelapiclient.model.CharactersQuery;
import com.karumi.marvelapiclient.model.MarvelResponse;
import java.util.Map;
import retrofit2.Call;

/**
 * Retrieves Character information given a  {@link CharactersQuery} or some simple params like the
 * character id. A valid {@link MarvelApiConfig} is needed.
 */
public final class CharacterApiClient extends MarvelApiClient {

  public CharacterApiClient(MarvelApiConfig marvelApiConfig) {
    super(marvelApiConfig);
  }

  public MarvelResponse<CharactersDto> getAll(int offset, int limit) throws MarvelApiException {
    CharactersQuery query =
        CharactersQuery.Builder.create().withOffset(offset).withLimit(limit).build();
    return getAll(query);
  }

  public MarvelResponse<CharactersDto> getAll(CharactersQuery charactersQuery)
      throws MarvelApiException {
    CharacterApiRest api = getApi(CharacterApiRest.class);

    Map<String, Object> queryAsMap = charactersQuery.toMap();
    Call<MarvelResponse<CharactersDto>> call = api.getCharacters(queryAsMap);
    return execute(call);
  }

  public MarvelResponse<CharacterDto> getCharacter(String characterId) throws MarvelApiException {
    if (characterId == null || characterId.isEmpty()) {
      throw new IllegalArgumentException("The CharacterId must not be null or empty");
    }
    CharacterApiRest api = getApi(CharacterApiRest.class);

    Call<MarvelResponse<CharactersDto>> call = api.getCharacter(characterId);
    MarvelResponse<CharactersDto> characters = execute(call);
    CharactersDto charactersDto = characters.getResponse();
    if (charactersDto != null && charactersDto.getCount() > 0) {
      CharacterDto characterDto = charactersDto.getCharacters().get(0);
      MarvelResponse<CharacterDto> characterResponse = new MarvelResponse<>(characters);
      characterResponse.setResponse(characterDto);
      return characterResponse;
    } else {
      throw new MarvelApiException("Character not found", null);
    }
  }
}
