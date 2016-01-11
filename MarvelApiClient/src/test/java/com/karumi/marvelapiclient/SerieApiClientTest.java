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

import com.karumi.marvelapiclient.model.CharacterResourceDto;
import com.karumi.marvelapiclient.model.ComicResourceDto;
import com.karumi.marvelapiclient.model.CreatorResourceDto;
import com.karumi.marvelapiclient.model.EventResourceDto;
import com.karumi.marvelapiclient.model.Format;
import com.karumi.marvelapiclient.model.MarvelImage;
import com.karumi.marvelapiclient.model.MarvelResources;
import com.karumi.marvelapiclient.model.MarvelResponse;
import com.karumi.marvelapiclient.model.MarvelUrl;
import com.karumi.marvelapiclient.model.OrderBy;
import com.karumi.marvelapiclient.model.SerieDto;
import com.karumi.marvelapiclient.model.SeriesDto;
import com.karumi.marvelapiclient.model.SeriesQuery;
import com.karumi.marvelapiclient.model.StoryResourceDto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.junit.Test;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SerieApiClientTest extends ApiClientTest {

  private static final String ANY_PUBLIC_KEY = "1234";
  private static final String ANY_PRIVATE_KEY = "abcd";
  private static final String ANY_URL = "http://fake.marvel.com/";
  private static final int ANY_OFFSET = 1;
  private static final int ANY_LIMIT = 10;
  private static final int INVALID_LIMIT = 0;
  private static final String ANY_TITLE = "name";
  private static final String ANY_START_TITLE = "name";
  private static final int ANY_START_YEAR = 2002;
  private static final int ANY_COMIC = 1;
  private static final int ANY_STORY = 1;
  private static final int ANY_CREATOR = 1;
  private static final int ANY_CHARACTER = 3;
  private static final int ANY_EVENT_1 = 1;
  private static final int ANY_EVENT_2 = 2;
  private static final int ANY_COLLABORATOR_1 = 1;
  private static final int ANY_COLLABORATOR_2 = 2;
  private static final String ANY_MODIFIED_SINCE = "2015-01-09T22:10:45-0800";
  private static final String ORDER_NAME_DESCEDANT_VALUE = "-name";
  private static final String INVALID_SERIE_ID = "";
  private static final String ANY_NOT_FOUND_ID = "1234";
  private static final String ANY_SERIE_ID = "123456";
  public static final String EVENTS_REQUEST = "1,2";

  @Test public void shouldSendOffsetAsLimitAsParamsWhenGetAll() throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();
    enqueueMockResponse();

    serieApiClient.getAll(ANY_OFFSET, ANY_LIMIT);

    assertRequestSentToContains("offset=" + ANY_OFFSET, "limit=" + ANY_LIMIT);
  }

  @Test(expected = IllegalArgumentException.class) public void shouldThrowExceptionWhenLimitIsZero()
      throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();
    enqueueMockResponse();

    serieApiClient.getAll(ANY_OFFSET, INVALID_LIMIT);
  }

  @Test public void shouldUseAllTheRequestParamsAddedFromQueryWhenSendIt() throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();
    enqueueMockResponse();

    SeriesQuery query = SeriesQuery.Builder.create()
        .withTitle(ANY_TITLE)
        .withTitleStartsWith(ANY_START_TITLE)
        .withStartYear(ANY_START_YEAR)
        .withLimit(ANY_LIMIT)
        .withOffset(ANY_OFFSET)
        .addCreator(ANY_CREATOR)
        .addCharacter(ANY_CHARACTER)
        .withModifiedSince(getAnyDate())
        .withOrderBy(OrderBy.NAME, false)
        .addEvent(getAnyEvents())
        .addComic(ANY_COMIC)
        .addStory(ANY_STORY)
        .withContains(Format.COMIC)
        .withSeriesType(SeriesQuery.SeriesType.COLLECTION)
        .build();

    serieApiClient.getAll(query);

    assertRequestSentToContains("startYear=" + ANY_START_YEAR, "creators=" + ANY_CREATOR,
        "characters=" + ANY_CHARACTER, "offset=" + ANY_OFFSET, "limit=" + ANY_LIMIT,
        "title=" + ANY_TITLE, "titleStartsWith=" + ANY_START_TITLE,
        "modifiedSince=" + ANY_MODIFIED_SINCE, "orderBy=" + ORDER_NAME_DESCEDANT_VALUE,
        "events=" + EVENTS_REQUEST, "comics=" + ANY_COMIC, "stories=" + ANY_STORY,
        "contains=" + Format.COMIC.toString(),
        "seriesType=" + SeriesQuery.SeriesType.COLLECTION.toString());
  }

  @Test(expected = MarvelAuthApiException.class)
  public void shouldThrowMarvelApiExceptionOnAuthHttpError() throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();
    enqueueMockResponse(401,
        "{\"code\":\"InvalidCredentials\",\"message\":\"That hash, timestamp and key "
            + "combination is invalid.\"}");

    try {
      serieApiClient.getAll(ANY_OFFSET, ANY_LIMIT);
    } catch (MarvelApiException e) {
      assertEquals("InvalidCredentials", e.getMarvelCode());
      assertEquals("That hash, timestamp and key combination is invalid.", e.getMessage());
      throw e;
    }
  }

  @Test public void shouldParseGetAllSeriesResponse() throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();
    enqueueMockResponse("getSeries.json");

    MarvelResponse<SeriesDto> series = serieApiClient.getAll(0, ANY_LIMIT);

    assertBasicMarvelResponse(series);
    assertGetAllSeriesResponseIsProperlyParsed(series);
  }

  @Test(expected = IllegalArgumentException.class) public void shouldNotAcceptEmptySerieId()
      throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();

    serieApiClient.getSerie(INVALID_SERIE_ID);
  }

  @Test public void shouldSendGetSerieRequestToTheCorrectEndpoint() throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();
    enqueueMockResponse("getSerie.json");

    serieApiClient.getSerie(ANY_SERIE_ID);

    assertRequestSentToContains("series/" + ANY_SERIE_ID);
  }

  @Test(expected = MarvelApiException.class)
  public void shouldReturnMarvelExceptionWhenTheIdDoesNotExist() throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();
    enqueueMockResponse(404, "{\"code\":404,\"status\":\"We couldn't find that serie\"}");

    try {
      serieApiClient.getSerie(ANY_NOT_FOUND_ID);
    } catch (MarvelApiException e) {
      assertEquals("404", e.getMarvelCode());
      assertEquals("We couldn't find that serie", e.getMessage());
      throw e;
    }
  }

  @Test public void shouldParseGetSerieResponse() throws Exception {
    SerieApiClient serieApiClient = givenSerieApiClient();
    enqueueMockResponse("getSerie.json");

    MarvelResponse<SerieDto> serie = serieApiClient.getSerie(ANY_SERIE_ID);

    assertBasicMarvelResponse(serie);

    SerieDto response = serie.getResponse();
    assertIs5Ronnin(response);
  }

  private List<Integer> getAnyEvents() {
    List<Integer> events = new ArrayList<>();
    events.add(ANY_EVENT_1);
    events.add(ANY_EVENT_2);
    return events;
  }

  private SerieApiClient givenSerieApiClient() {

    Retrofit retrofit = new Retrofit.Builder().baseUrl(getBaseEndpoint())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    MarvelApiConfig marvelApiConfig =
        new MarvelApiConfig.Builder(ANY_PUBLIC_KEY, ANY_PRIVATE_KEY).baseUrl(ANY_URL)
            .retrofit(retrofit)
            .build();
    return new SerieApiClient(marvelApiConfig);
  }

  private void assertGetAllSeriesResponseIsProperlyParsed(MarvelResponse<SeriesDto> series) {
    SeriesDto seriesDto = series.getResponse();
    assertEquals(10, seriesDto.getCount());
    assertEquals(10, seriesDto.getLimit());
    assertEquals(0, seriesDto.getOffset());
    assertEquals(1823, seriesDto.getTotal());

    SerieDto serieDto = seriesDto.getSeries().get(0);
    assertIs5Ronnin(serieDto);
  }

  private void assertIs5Ronnin(SerieDto serieDto) {
    assertEquals("15276", serieDto.getId());
    assertEquals("5 Ronin (2011)", serieDto.getTitle());
    assertEquals("description", serieDto.getDescription());
    assertEquals("2011-05-20T16:25:29-0400", serieDto.getModified());
    assertEquals(2011, serieDto.getStartYear());
    assertEquals(2011, serieDto.getEndYear());
    assertEquals("+18", serieDto.getRating());

    assertEquals("http://gateway.marvel.com/v1/public/series/15276", serieDto.getResourceURI());

    MarvelUrl marvelUrl = serieDto.getUrls().get(0);
    assertEquals("detail", marvelUrl.getType());
    assertEquals("http://marvel.com/comics/series/15276/5_ronin_2011?utm_campaign=apiRef&utm_source"
        + "=838a08a2f4c39fa3fd218b1b2d43f19a", marvelUrl.getUrl());

    MarvelImage thumbnail = serieDto.getThumbnail();
    assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
        thumbnail.getPath());
    assertEquals("jpg", thumbnail.getExtension());

    MarvelResources<CreatorResourceDto> creators = serieDto.getCreators();
    assertEquals(8, creators.getAvailable());
    assertEquals("http://gateway.marvel.com/v1/public/series/15276/creators",
        creators.getCollectionUri());
    assertEquals(8, creators.getReturned());
    CreatorResourceDto creatorResourceDto = creators.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/creators/750",
        creatorResourceDto.getResourceUri());
    assertEquals("David Aja", creatorResourceDto.getName());
    assertEquals("penciller (cover)", creatorResourceDto.getRole());

    MarvelResources<CharacterResourceDto> characters = serieDto.getCharacters();
    assertEquals(4, characters.getAvailable());
    assertEquals(4, characters.getReturned());
    CharacterResourceDto characterResourceDto = characters.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/characters/1009268",
        characterResourceDto.getResourceUri());
    assertEquals("Deadpool", characterResourceDto.getName());

    MarvelResources<StoryResourceDto> stories = serieDto.getStories();
    assertEquals(12, stories.getAvailable());
    assertEquals("http://gateway.marvel.com/v1/public/series/15276/stories",
        stories.getCollectionUri());
    assertEquals(12, stories.getReturned());
    StoryResourceDto storyResourceDto = stories.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/stories/80038",
        storyResourceDto.getResourceUri());
    assertEquals("Interior #80038", storyResourceDto.getName());
    assertEquals("interiorStory", storyResourceDto.getType());

    MarvelResources<ComicResourceDto> comics = serieDto.getComics();
    assertEquals(2, comics.getAvailable());
    assertEquals("http://gateway.marvel.com/v1/public/series/15276/comics",
        comics.getCollectionUri());
    assertEquals(2, comics.getReturned());
    ComicResourceDto comicResourceDto = comics.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/comics/41112",
        comicResourceDto.getResourceUri());
    assertEquals("5 Ronin (Hardcover)", comicResourceDto.getName());

    MarvelResources<EventResourceDto> events = serieDto.getEvents();
    assertEquals(0, events.getAvailable());
    assertEquals(0, events.getReturned());
    assertEquals("http://gateway.marvel.com/v1/public/series/15276/events",
        events.getCollectionUri());
    assertTrue(events.getItems().isEmpty());
  }

  public Date getAnyDate() {
    Calendar instance = Calendar.getInstance();
    instance.setTimeZone(TimeZone.getTimeZone(ANY_TIME_ZONE));
    instance.set(Calendar.YEAR, 2015);
    instance.set(Calendar.MONTH, Calendar.JANUARY);
    instance.set(Calendar.DAY_OF_MONTH, 9);
    instance.set(Calendar.HOUR_OF_DAY, 22);
    instance.set(Calendar.MINUTE, 10);
    instance.set(Calendar.SECOND, 45);
    return instance.getTime();
  }
}