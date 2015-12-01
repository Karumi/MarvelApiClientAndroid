package com.karumi.marvelapiclient;

import com.karumi.marvelapiclient.model.CharactersDto;
import com.karumi.marvelapiclient.model.MarvelResponse;
import java.util.Map;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface CharacterApiRest {
  @GET("characters") Call<MarvelResponse<CharactersDto>> getCharacters(
      @QueryMap Map<String, Object> characterFilter);
}
