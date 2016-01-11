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

import com.karumi.marvelapiclient.model.MarvelResponse;
import com.karumi.marvelapiclient.model.SerieDto;
import com.karumi.marvelapiclient.model.SeriesDto;
import com.karumi.marvelapiclient.model.SeriesQuery;
import java.util.Map;
import retrofit.Call;

/**
 * Retrieves Series information given a  {@link SeriesQuery} or some simple params like the
 * serie id. A valid {@link MarvelApiConfig} is needed.
 */
public final class SerieApiClient extends MarvelApiClient {

  public SerieApiClient(MarvelApiConfig marvelApiConfig) {
    super(marvelApiConfig);
  }

  public MarvelResponse<SeriesDto> getAll(int offset, int limit) throws MarvelApiException {
    SeriesQuery query = SeriesQuery.Builder.create().withOffset(offset).withLimit(limit).build();
    return getAll(query);
  }

  public MarvelResponse<SeriesDto> getAll(SeriesQuery seriesQuery) throws MarvelApiException {
    SerieApiRest api = getApi(SerieApiRest.class);

    Map<String, Object> queryAsMap = seriesQuery.toMap();
    Call<MarvelResponse<SeriesDto>> call = api.getSeries(queryAsMap);
    return execute(call);
  }

  public MarvelResponse<SerieDto> getSerie(String serieId) throws MarvelApiException {
    if (serieId == null || serieId.isEmpty()) {
      throw new IllegalArgumentException("The serieId must not be null or empty");
    }
    SerieApiRest api = getApi(SerieApiRest.class);

    Call<MarvelResponse<SeriesDto>> call = api.getSerie(serieId);
    MarvelResponse<SeriesDto> series = execute(call);
    SeriesDto seriesDto = series.getResponse();
    if (seriesDto != null && seriesDto.getCount() > 0) {
      SerieDto serieDto = seriesDto.getSeries().get(0);
      MarvelResponse<SerieDto> serieResponse = new MarvelResponse<>(series);
      serieResponse.setResponse(serieDto);
      return serieResponse;
    } else {
      throw new MarvelApiException("Serie not found", null);
    }
  }
}
