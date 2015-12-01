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
