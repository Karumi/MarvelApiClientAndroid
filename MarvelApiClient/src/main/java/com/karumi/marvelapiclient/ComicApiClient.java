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

import com.karumi.marvelapiclient.model.ComicDto;
import com.karumi.marvelapiclient.model.ComicsDto;
import com.karumi.marvelapiclient.model.ComicsQuery;
import com.karumi.marvelapiclient.model.MarvelResponse;
import java.util.Map;
import retrofit2.Call;

/**
 * Retrieves Comics information given a  {@link ComicsQuery} or some simple params like the
 * character id. A valid {@link MarvelApiConfig} is needed.
 */
public final class ComicApiClient extends MarvelApiClient {

  public ComicApiClient(MarvelApiConfig marvelApiConfig) {
    super(marvelApiConfig);
  }

  public MarvelResponse<ComicsDto> getAll(int offset, int limit) throws MarvelApiException {
    ComicsQuery query = ComicsQuery.Builder.create().withOffset(offset).withLimit(limit).build();
    return getAll(query);
  }

  public MarvelResponse<ComicsDto> getAll(ComicsQuery comicsQuery) throws MarvelApiException {
    ComicApiRest api = getApi(ComicApiRest.class);

    Map<String, Object> queryAsMap = comicsQuery.toMap();
    Call<MarvelResponse<ComicsDto>> call = api.getComics(queryAsMap);
    return execute(call);
  }

  public MarvelResponse<ComicDto> getComic(String comicId) throws MarvelApiException {
    if (comicId == null || comicId.isEmpty()) {
      throw new IllegalArgumentException("The ComicId must not be null or empty");
    }
    ComicApiRest api = getApi(ComicApiRest.class);

    Call<MarvelResponse<ComicsDto>> call = api.getComic(comicId);
    MarvelResponse<ComicsDto> comics = execute(call);
    ComicsDto comicsDto = comics.getResponse();
    if (comicsDto != null && comicsDto.getCount() > 0) {
      ComicDto comicDto = comicsDto.getComics().get(0);
      MarvelResponse<ComicDto> comicResponse = new MarvelResponse<>(comics);
      comicResponse.setResponse(comicDto);
      return comicResponse;
    } else {
      throw new MarvelApiException("Comic not found", null);
    }
  }
}
