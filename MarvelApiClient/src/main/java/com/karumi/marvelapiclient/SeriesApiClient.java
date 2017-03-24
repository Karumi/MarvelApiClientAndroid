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

import com.karumi.marvelapiclient.model.ComicsDto;
import com.karumi.marvelapiclient.model.ComicsQuery;
import com.karumi.marvelapiclient.model.MarvelResponse;
import com.karumi.marvelapiclient.model.SeriesCollectionDto;
import com.karumi.marvelapiclient.model.SeriesDto;
import com.karumi.marvelapiclient.model.SeriesQuery;
import java.util.Map;
import retrofit2.Call;

/**
 * Retrieves Series information given a  {@link SeriesQuery} or some simple params like the
 * serie id. A valid {@link MarvelApiConfig} is needed.
 */
public final class SeriesApiClient extends MarvelApiClient {

  public SeriesApiClient(MarvelApiConfig marvelApiConfig) {
    super(marvelApiConfig);
  }

  public MarvelResponse<SeriesCollectionDto> getAll(int offset, int limit)
      throws MarvelApiException {
    SeriesQuery query = SeriesQuery.Builder.create().withOffset(offset).withLimit(limit).build();
    return getAll(query);
  }

  public MarvelResponse<SeriesCollectionDto> getAll(SeriesQuery seriesQuery)
      throws MarvelApiException {
    SeriesApiRest api = getApi(SeriesApiRest.class);

    Map<String, Object> queryAsMap = seriesQuery.toMap();
    Call<MarvelResponse<SeriesCollectionDto>> call = api.getSeries(queryAsMap);
    return execute(call);
  }

  public MarvelResponse<SeriesDto> getSeriesById(String seriesId) throws MarvelApiException {
    if (seriesId == null || seriesId.isEmpty()) {
      throw new IllegalArgumentException("The seriesId must not be null or empty");
    }
    SeriesApiRest api = getApi(SeriesApiRest.class);

    Call<MarvelResponse<SeriesCollectionDto>> call = api.getSerie(seriesId);
    MarvelResponse<SeriesCollectionDto> series = execute(call);
    SeriesCollectionDto seriesCollectionDto = series.getResponse();
    if (seriesCollectionDto != null && seriesCollectionDto.getCount() > 0) {
      SeriesDto seriesDto = seriesCollectionDto.getSeries().get(0);
      MarvelResponse<SeriesDto> serieResponse = new MarvelResponse<>(series);
      serieResponse.setResponse(seriesDto);
      return serieResponse;
    } else {
      throw new MarvelApiException("Series not found", null);
    }
  }

  public MarvelResponse<ComicsDto> getComicsBySeries(String seriesId, int offset, int limit)
      throws MarvelApiException {
    ComicsQuery query = ComicsQuery.Builder.create().withOffset(offset).withLimit(limit).build();
    return getComicsBySeries(seriesId, query);
  }

  public MarvelResponse<ComicsDto> getComicsBySeries(String seriesId, ComicsQuery comicsQuery)
      throws MarvelApiException {
    if (seriesId == null || seriesId.isEmpty()) {
      throw new IllegalArgumentException("The seriesId must not be null or empty");
    }
    SeriesApiRest api = getApi(SeriesApiRest.class);

    Map<String, Object> queryMap = comicsQuery.toMap();
    Call<MarvelResponse<ComicsDto>> call = api.getComicsBySerie(seriesId, queryMap);

    return execute(call);
  }
}
