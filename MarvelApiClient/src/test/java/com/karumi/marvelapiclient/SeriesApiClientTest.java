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
import com.karumi.marvelapiclient.model.ComicsQuery;
import com.karumi.marvelapiclient.model.CreatorResourceDto;
import com.karumi.marvelapiclient.model.EventResourceDto;
import com.karumi.marvelapiclient.model.Format;
import com.karumi.marvelapiclient.model.MarvelImage;
import com.karumi.marvelapiclient.model.MarvelResources;
import com.karumi.marvelapiclient.model.MarvelResponse;
import com.karumi.marvelapiclient.model.MarvelUrl;
import com.karumi.marvelapiclient.model.OrderBy;
import com.karumi.marvelapiclient.model.SeriesCollectionDto;
import com.karumi.marvelapiclient.model.SeriesDto;
import com.karumi.marvelapiclient.model.SeriesQuery;
import com.karumi.marvelapiclient.model.StoryResourceDto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeriesApiClientTest extends ApiClientTest {

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
  private static final String INVALID_SERIES_ID = "";
  private static final String ANY_NOT_FOUND_ID = "1234";
  private static final String ANY_SERIE_ID = "123456";
  private static final String EVENTS_REQUEST = "1,2";
  private static final int ANY_SERIE = 1;
  private static final int ANY_SHARED_APPEARANCES = 4;
  private static final String COLLABORATORS_REQUEST = "1,2";
  private static final String LAST_WEEK_REQUEST = "last%20week";
  private static final String ANY_DATE_RANGE = "2015-01-09T22:10:45-0800";

  private static final int ANY_ISSUE_NUMBER = 1;
  private static final String ANY_DIAMOND_CODE = "any";
  private static final String ANY_UPC = "1";
  private static final String ANY_ISBN = "1";
  private static final String ANY_EAN = "1";
  private static final String ANY_ISSN = "1";

  @Test public void shouldSendOffsetAsLimitAsParamsWhenGetAll() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse();

    seriesApiClient.getAll(ANY_OFFSET, ANY_LIMIT);

    assertRequestSentToContains("offset=" + ANY_OFFSET, "limit=" + ANY_LIMIT);
  }

  @Test(expected = IllegalArgumentException.class) public void shouldThrowExceptionWhenLimitIsZero()
      throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse();

    seriesApiClient.getAll(ANY_OFFSET, INVALID_LIMIT);
  }

  @Test public void shouldUseAllTheRequestParamsAddedFromQueryWhenSendIt() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
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

    seriesApiClient.getAll(query);

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
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse(401,
        "{\"code\":\"InvalidCredentials\",\"message\":\"That hash, timestamp and key "
            + "combination is invalid.\"}");

    try {
      seriesApiClient.getAll(ANY_OFFSET, ANY_LIMIT);
    } catch (MarvelApiException e) {
      assertEquals("InvalidCredentials", e.getMarvelCode());
      assertEquals("That hash, timestamp and key combination is invalid.", e.getMessage());
      throw e;
    }
  }

  @Test public void shouldParseGetAllSeriesResponse() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse("getSeriesCollection.json");

    MarvelResponse<SeriesCollectionDto> series = seriesApiClient.getAll(0, ANY_LIMIT);

    assertBasicMarvelResponse(series);
    assertGetAllSeriesResponseIsProperlyParsed(series);
  }

  @Test(expected = IllegalArgumentException.class) public void shouldNotAcceptEmptySeriesId()
      throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();

    seriesApiClient.getSeriesById(INVALID_SERIES_ID);
  }

  @Test public void shouldSendGetSeriesRequestToTheCorrectEndpoint() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse("getSeriesCollection.json");

    seriesApiClient.getSeriesById(ANY_SERIE_ID);

    assertRequestSentToContains("series/" + ANY_SERIE_ID);
  }

  @Test(expected = MarvelApiException.class)
  public void shouldReturnMarvelExceptionWhenTheIdDoesNotExist() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse(404, "{\"code\":404,\"status\":\"We couldn't find that series\"}");

    try {
      seriesApiClient.getSeriesById(ANY_NOT_FOUND_ID);
    } catch (MarvelApiException e) {
      assertEquals("404", e.getMarvelCode());
      assertEquals("We couldn't find that series", e.getMessage());
      throw e;
    }
  }

  @Test public void shouldParseGetSeriesResponse() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse("getSeries.json");

    MarvelResponse<SeriesDto> series = seriesApiClient.getSeriesById(ANY_SERIE_ID);

    assertBasicMarvelResponse(series);

    SeriesDto response = series.getResponse();
    assertIs5Ronnin(response);
  }

  @Test public void shouldSendGetComicBySeriesRequestToTheCorrectEndpoint() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse("getComics.json");

    ComicsQuery query = ComicsQuery.Builder.create()
        .withFormat(Format.COMIC)
        .withFormatType(ComicsQuery.FormatType.COLLECTION)
        .withNoVariants()
        .withDateDescriptor(ComicsQuery.DateDescriptor.LAST_WEEK)
        .withDateRange(getAnyDate())
        .withTitle(ANY_TITLE)
        .withTitleStartsWith(ANY_START_TITLE)
        .withStartYear(ANY_START_YEAR)
        .withIssueNumber(ANY_ISSUE_NUMBER)
        .withDiamondCode(ANY_DIAMOND_CODE)
        .withUpc(ANY_UPC)
        .withIsbn(ANY_ISBN)
        .withEan(ANY_EAN)
        .withIssn(ANY_ISSN)
        .withHasDigitalIssue()
        .withLimit(ANY_LIMIT)
        .withOffset(ANY_OFFSET)
        .addCreator(ANY_CREATOR)
        .addCharacter(ANY_CHARACTER)
        .withModifiedSince(getAnyDate())
        .withOrderBy(OrderBy.NAME, false)
        .addEvent(getAnyEvents())
        .addSerie(ANY_SERIE)
        .addStory(ANY_STORY)
        .addSharedAppearance(ANY_SHARED_APPEARANCES)
        .addCollaborator(getAnyCollaborators())
        .build();

    seriesApiClient.getComicsBySeries(ANY_SERIE_ID, query);

    assertRequestSentToContains("series/" + ANY_SERIE_ID, "format=" + Format.COMIC.toString(),
        "formatType=" + ComicsQuery.FormatType.COLLECTION, "noVariants=true",
        "dateDescriptor=" + LAST_WEEK_REQUEST, "dateRange=" + ANY_DATE_RANGE,
        "startYear=" + ANY_START_YEAR, "issueNumber=" + ANY_ISSUE_NUMBER,
        "diamondCode=" + ANY_DIAMOND_CODE, "upc=" + ANY_UPC, "isbn=" + ANY_ISBN, "ean=" + ANY_EAN,
        "issn=" + ANY_ISSN, "hasDigitalIssue=true", "creators=" + ANY_CREATOR,
        "characters=" + ANY_CHARACTER, "offset=" + ANY_OFFSET,
        "appearances=" + ANY_SHARED_APPEARANCES, "collaborators=" + COLLABORATORS_REQUEST,
        "limit=" + ANY_LIMIT, "title=" + ANY_TITLE, "titleStartsWith=" + ANY_START_TITLE,
        "modifiedSince=" + ANY_MODIFIED_SINCE, "orderBy=" + ORDER_NAME_DESCEDANT_VALUE,
        "events=" + EVENTS_REQUEST, "series=" + ANY_SERIE, "stories=" + ANY_STORY);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenCallGetComicBySerieAndLimitIsZero() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse();

    seriesApiClient.getComicsBySeries(ANY_SERIE_ID, ANY_OFFSET, INVALID_LIMIT);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenCallGetComicBySerieAndSerieIdIsEmpty() throws Exception {
    SeriesApiClient seriesApiClient = givenSeriesApiClient();
    enqueueMockResponse();

    seriesApiClient.getComicsBySeries("", ANY_OFFSET, INVALID_LIMIT);
  }

  private List<Integer> getAnyCollaborators() {
    List<Integer> collaborators = new ArrayList<>();
    collaborators.add(ANY_COLLABORATOR_1);
    collaborators.add(ANY_COLLABORATOR_2);
    return collaborators;
  }

  private List<Integer> getAnyEvents() {
    List<Integer> events = new ArrayList<>();
    events.add(ANY_EVENT_1);
    events.add(ANY_EVENT_2);
    return events;
  }

  private SeriesApiClient givenSeriesApiClient() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(getBaseEndpoint())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    MarvelApiConfig marvelApiConfig =
        new MarvelApiConfig.Builder(ANY_PUBLIC_KEY, ANY_PRIVATE_KEY).baseUrl(ANY_URL)
            .retrofit(retrofit)
            .build();
    return new SeriesApiClient(marvelApiConfig);
  }

  private void assertGetAllSeriesResponseIsProperlyParsed(
      MarvelResponse<SeriesCollectionDto> series) {
    SeriesCollectionDto seriesCollectionDto = series.getResponse();
    assertEquals(10, seriesCollectionDto.getCount());
    assertEquals(10, seriesCollectionDto.getLimit());
    assertEquals(0, seriesCollectionDto.getOffset());
    assertEquals(1823, seriesCollectionDto.getTotal());

    SeriesDto seriesDto = seriesCollectionDto.getSeries().get(0);
    assertIs5Ronnin(seriesDto);
  }

  private void assertIs5Ronnin(SeriesDto seriesDto) {
    assertEquals("15276", seriesDto.getId());
    assertEquals("5 Ronin (2011)", seriesDto.getTitle());
    assertEquals("description", seriesDto.getDescription());
    assertEquals("2011-05-20T16:25:29-0400", seriesDto.getModified());
    assertEquals(2011, seriesDto.getStartYear());
    assertEquals(2011, seriesDto.getEndYear());
    assertEquals("+18", seriesDto.getRating());

    assertEquals("http://gateway.marvel.com/v1/public/series/15276", seriesDto.getResourceURI());

    MarvelUrl marvelUrl = seriesDto.getUrls().get(0);
    assertEquals("detail", marvelUrl.getType());
    assertEquals("http://marvel.com/comics/series/15276/5_ronin_2011?utm_campaign=apiRef&utm_source"
        + "=838a08a2f4c39fa3fd218b1b2d43f19a", marvelUrl.getUrl());

    MarvelImage thumbnail = seriesDto.getThumbnail();
    assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
        thumbnail.getPath());
    assertEquals("jpg", thumbnail.getExtension());

    MarvelResources<CreatorResourceDto> creators = seriesDto.getCreators();
    assertEquals(8, creators.getAvailable());
    assertEquals("http://gateway.marvel.com/v1/public/series/15276/creators",
        creators.getCollectionUri());
    assertEquals(8, creators.getReturned());
    CreatorResourceDto creatorResourceDto = creators.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/creators/750",
        creatorResourceDto.getResourceUri());
    assertEquals("David Aja", creatorResourceDto.getName());
    assertEquals("penciller (cover)", creatorResourceDto.getRole());

    MarvelResources<CharacterResourceDto> characters = seriesDto.getCharacters();
    assertEquals(4, characters.getAvailable());
    assertEquals(4, characters.getReturned());
    CharacterResourceDto characterResourceDto = characters.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/characters/1009268",
        characterResourceDto.getResourceUri());
    assertEquals("Deadpool", characterResourceDto.getName());

    MarvelResources<StoryResourceDto> stories = seriesDto.getStories();
    assertEquals(12, stories.getAvailable());
    assertEquals("http://gateway.marvel.com/v1/public/series/15276/stories",
        stories.getCollectionUri());
    assertEquals(12, stories.getReturned());
    StoryResourceDto storyResourceDto = stories.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/stories/80038",
        storyResourceDto.getResourceUri());
    assertEquals("Interior #80038", storyResourceDto.getName());
    assertEquals("interiorStory", storyResourceDto.getType());

    MarvelResources<ComicResourceDto> comics = seriesDto.getComics();
    assertEquals(2, comics.getAvailable());
    assertEquals("http://gateway.marvel.com/v1/public/series/15276/comics",
        comics.getCollectionUri());
    assertEquals(2, comics.getReturned());
    ComicResourceDto comicResourceDto = comics.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/comics/41112",
        comicResourceDto.getResourceUri());
    assertEquals("5 Ronin (Hardcover)", comicResourceDto.getName());

    MarvelResources<EventResourceDto> events = seriesDto.getEvents();
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
