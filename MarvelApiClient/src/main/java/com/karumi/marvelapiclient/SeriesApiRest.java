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
import com.karumi.marvelapiclient.model.MarvelResponse;
import com.karumi.marvelapiclient.model.SeriesCollectionDto;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

interface SeriesApiRest {
  @GET("series") Call<MarvelResponse<SeriesCollectionDto>> getSeries(
      @QueryMap Map<String, Object> seriesFilter);

  @GET("series/{id}") Call<MarvelResponse<SeriesCollectionDto>> getSerie(
      @Path("id") String serieId);

  @GET("series/{id}/comics") Call<MarvelResponse<ComicsDto>> getComicsBySerie(
      @Path("id") String serieId, @QueryMap Map<String, Object> comicFilter);
}
