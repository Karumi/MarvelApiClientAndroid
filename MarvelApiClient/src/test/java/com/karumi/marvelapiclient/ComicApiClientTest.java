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
import com.karumi.marvelapiclient.model.ComicDto;
import com.karumi.marvelapiclient.model.ComicResourceDto;
import com.karumi.marvelapiclient.model.ComicsDto;
import com.karumi.marvelapiclient.model.ComicsQuery;
import com.karumi.marvelapiclient.model.CreatorResourceDto;
import com.karumi.marvelapiclient.model.EventResourceDto;
import com.karumi.marvelapiclient.model.Format;
import com.karumi.marvelapiclient.model.MarvelDate;
import com.karumi.marvelapiclient.model.MarvelImage;
import com.karumi.marvelapiclient.model.MarvelPrice;
import com.karumi.marvelapiclient.model.MarvelResources;
import com.karumi.marvelapiclient.model.MarvelResponse;
import com.karumi.marvelapiclient.model.MarvelUrl;
import com.karumi.marvelapiclient.model.OrderBy;
import com.karumi.marvelapiclient.model.StoryResourceDto;
import com.karumi.marvelapiclient.model.TextObject;
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

public class ComicApiClientTest extends ApiClientTest {

  private static final String ANY_PUBLIC_KEY = "1234";
  private static final String ANY_PRIVATE_KEY = "abcd";
  private static final String ANY_URL = "http://fake.marvel.com/";
  private static final int ANY_OFFSET = 1;
  private static final int ANY_LIMIT = 10;
  private static final int INVALID_LIMIT = 0;
  private static final String ANY_TITLE = "name";
  private static final String ANY_START_TITLE = "name";
  private static final int ANY_START_YEAR = 2002;
  private static final int ANY_ISSUE_NUMBER = 1;
  private static final String ANY_DIAMOND_CODE = "any";
  private static final String ANY_UPC = "1";
  private static final String ANY_ISBN = "1";
  private static final String ANY_EAN = "1";
  private static final String ANY_ISSN = "1";
  private static final int ANY_SERIE = 1;
  private static final int ANY_STORY = 1;
  private static final int ANY_CREATOR = 1;
  private static final int ANY_CHARACTER = 3;
  private static final int ANY_SHARED_APPEARANCES = 4;
  private static final int ANY_EVENT_1 = 1;
  private static final int ANY_EVENT_2 = 2;
  private static final int ANY_COLLABORATOR_1 = 1;
  private static final int ANY_COLLABORATOR_2 = 2;
  private static final String ANY_DATE_RANGE = "2015-01-09T22:10:45-0800";
  private static final String ANY_MODIFIED_SINCE = "2015-01-09T22:10:45-0800";
  private static final String ORDER_NAME_DESCEDANT_VALUE = "-name";
  private static final String INVALID_COMIC_ID = "";
  private static final String ANY_NOT_FOUND_ID = "1234";
  private static final String ANY_COMIC_ID = "123456";
  private static final String EVENTS_REQUEST = "1,2";
  private static final String COLLABORATORS_REQUEST = "1,2";
  private static final String LAST_WEEK_REQUEST = "last%20week";

  @Test public void shouldRequestComicsByOffsetAndLimitWithAsParams() throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();
    enqueueMockResponse();

    comicApiClient.getAll(ANY_OFFSET, ANY_LIMIT);

    assertRequestSentToContains("offset=" + ANY_OFFSET, "limit=" + ANY_LIMIT);
  }

  @Test(expected = IllegalArgumentException.class) public void shouldThrowExceptionWhenLimitIsZero()
      throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();
    enqueueMockResponse();

    comicApiClient.getAll(ANY_OFFSET, INVALID_LIMIT);
  }

  @Test public void shouldUseAllTheRequestParamsAddedFromQueryWhenSendIt() throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();
    enqueueMockResponse();

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

    comicApiClient.getAll(query);

    assertRequestSentToContains("format=" + Format.COMIC.toString(),
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

  @Test(expected = MarvelAuthApiException.class)
  public void shouldThrowMarvelApiExceptionOnAuthHttpError() throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();
    enqueueMockResponse(401,
        "{\"code\":\"InvalidCredentials\",\"message\":\"That hash, timestamp and key "
            + "combination is invalid.\"}");

    try {
      comicApiClient.getAll(ANY_OFFSET, ANY_LIMIT);
    } catch (MarvelApiException e) {
      assertEquals("InvalidCredentials", e.getMarvelCode());
      assertEquals("That hash, timestamp and key combination is invalid.", e.getMessage());
      throw e;
    }
  }

  @Test public void shouldParseGetAllComicsResponse() throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();
    enqueueMockResponse("getComics.json");

    MarvelResponse<ComicsDto> comics = comicApiClient.getAll(0, ANY_LIMIT);

    assertBasicMarvelResponse(comics);
    assertGetAllComicsResponseIsProperlyParsed(comics);
  }

  @Test(expected = IllegalArgumentException.class) public void shouldNotAcceptEmptyComicIds()
      throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();

    comicApiClient.getComic(INVALID_COMIC_ID);
  }

  @Test public void shouldSendGetComicRequestToTheCorrectEndpoint() throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();
    enqueueMockResponse("getComic.json");

    comicApiClient.getComic(ANY_COMIC_ID);

    assertRequestSentToContains("comics/" + ANY_COMIC_ID);
  }

  @Test(expected = MarvelApiException.class)
  public void shouldReturnMarvelExceptionWhenTheIdDoesNotExist() throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();
    enqueueMockResponse(404, "{\"code\":404,\"status\":\"We couldn't find that comic\"}");

    try {
      comicApiClient.getComic(ANY_NOT_FOUND_ID);
    } catch (MarvelApiException e) {
      assertEquals("404", e.getMarvelCode());
      assertEquals("We couldn't find that comic", e.getMessage());
      throw e;
    }
  }

  @Test public void shouldParseGetComicResponse() throws Exception {
    ComicApiClient comicApiClient = givenComicApiClient();
    enqueueMockResponse("getComic.json");

    MarvelResponse<ComicDto> comic = comicApiClient.getComic(ANY_COMIC_ID);

    assertBasicMarvelResponse(comic);

    ComicDto response = comic.getResponse();
    assertIsLornaTheJungleGirl6(response);
  }

  private List<Integer> getAnyEvents() {
    List<Integer> events = new ArrayList<>();
    events.add(ANY_EVENT_1);
    events.add(ANY_EVENT_2);
    return events;
  }

  private List<Integer> getAnyCollaborators() {
    List<Integer> collaborators = new ArrayList<>();
    collaborators.add(ANY_COLLABORATOR_1);
    collaborators.add(ANY_COLLABORATOR_2);
    return collaborators;
  }

  private ComicApiClient givenComicApiClient() {

    Retrofit retrofit = new Retrofit.Builder().baseUrl(getBaseEndpoint())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    MarvelApiConfig marvelApiConfig =
        new MarvelApiConfig.Builder(ANY_PUBLIC_KEY, ANY_PRIVATE_KEY).baseUrl(ANY_URL)
            .retrofit(retrofit)
            .build();
    return new ComicApiClient(marvelApiConfig);
  }

  private void assertGetAllComicsResponseIsProperlyParsed(MarvelResponse<ComicsDto> comics) {
    ComicsDto comicsDto = comics.getResponse();
    assertEquals(10, comicsDto.getCount());
    assertEquals(10, comicsDto.getLimit());
    assertEquals(0, comicsDto.getOffset());
    assertEquals(35767, comicsDto.getTotal());

    ComicDto comicDto = comicsDto.getComics().get(0);
    assertIsLornaTheJungleGirl6(comicDto);
  }

  private void assertIsLornaTheJungleGirl6(ComicDto comicDto) {
    assertEquals("42882", comicDto.getId());
    assertEquals(26110, comicDto.getDigitalId());
    assertEquals("Lorna the Jungle Girl (1954) #6", comicDto.getTitle());
    assertEquals(6, comicDto.getIssueNumber(), 0);
    assertEquals("variant", comicDto.getVariantDescription());
    assertEquals("description", comicDto.getDescription());
    assertEquals("2015-10-15T11:13:52-0400", comicDto.getModified());
    assertEquals("1", comicDto.getIsbn());
    assertEquals("2", comicDto.getUpc());
    assertEquals("3", comicDto.getEan());
    assertEquals("4", comicDto.getIssn());
    assertEquals("5", comicDto.getDiamondCode());
    assertEquals("Comic", comicDto.getFormat());
    assertEquals(32, comicDto.getPageCount());
    TextObject textObject = comicDto.getTextObjects().get(0);
    assertEquals("issue_solicit_text", textObject.getType());
    assertEquals("en-us", textObject.getLanguage());
    assertEquals("From the award-winning team that brought you Daredevil and Spider-Woman "
        + "comes another explosive chapter of one of the most controversial "
        + "creator-owned comics being published today. Scarlet has declared "
        + "war on a city that refuses to stop the corruption that is strangling "
        + "it from within, and now she&#39;ll be forced to make her most dangerous "
        + "move yet: letting them capture her.", textObject.getText());

    assertEquals("http://gateway.marvel.com/v1/public/comics/42882", comicDto.getResourceURI());
    MarvelUrl marvelUrl = comicDto.getUrls().get(0);
    assertEquals("detail", marvelUrl.getType());
    assertEquals(
        "http://marvel.com/comics/issue/42882/lorna_the_jungle_girl_1954_6?utm_campaign=apiRef"
            + "&utm_source=838a08a2f4c39fa3fd218b1b2d43f19a", marvelUrl.getUrl());

    ComicResourceDto series = comicDto.getSeries();
    assertEquals("http://gateway.marvel.com/v1/public/series/16355", series.getResourceUri());
    assertEquals("Lorna the Jungle Girl (1954 - 1957)", series.getName());

    MarvelDate marvelDate = comicDto.getDates().get(0);
    assertEquals("onsaleDate", marvelDate.getType());
    assertEquals("2054-03-01T00:00:00-0500", marvelDate.getDate());

    MarvelPrice marvelPrice = comicDto.getPrices().get(0);
    assertEquals("printPrice", marvelPrice.getType());
    assertEquals(0, marvelPrice.getPrice(), 0);

    MarvelImage thumbnail = comicDto.getThumbnail();
    assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/9/40/50b4fc783d30f", thumbnail.getPath());
    assertEquals("jpg", thumbnail.getExtension());

    MarvelImage marvelImage = comicDto.getImages().get(0);
    assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/9/40/50b4fc783d30f",
        marvelImage.getPath());
    assertEquals("jpg", marvelImage.getExtension());

    MarvelResources<CreatorResourceDto> creators = comicDto.getCreators();
    assertEquals(1, creators.getAvailable());
    assertEquals("http://gateway.marvel.com/v1/public/comics/41530/creators",
        creators.getCollectionUri());
    assertEquals(1, creators.getReturned());
    CreatorResourceDto creatorResourceDto = creators.getItems().get(0);
    assertEquals("http://gateway.marvel.com/v1/public/creators/4430",
        creatorResourceDto.getResourceUri());
    assertEquals("Jeff Youngquist", creatorResourceDto.getName());
    assertEquals("editor", creatorResourceDto.getRole());

    MarvelResources<CharacterResourceDto> characters = comicDto.getCharacters();
    assertEquals(0, characters.getAvailable());
    assertEquals(0, characters.getReturned());
    assertTrue(characters.getItems().isEmpty());

    MarvelResources<StoryResourceDto> stories = comicDto.getStories();
    assertEquals(1, stories.getAvailable());
    assertEquals("http://gateway.marvel.com/v1/public/comics/42882/stories",
        stories.getCollectionUri());
    assertEquals(1, stories.getReturned());
    StoryResourceDto storyResourceDto = stories.getItems().get(0);
    assertEquals("cover from Lorna the Jungle Girl #6", storyResourceDto.getName());
    assertEquals("cover", storyResourceDto.getType());

    MarvelResources<EventResourceDto> events = comicDto.getEvents();
    assertEquals(0, events.getAvailable());
    assertEquals(0, events.getReturned());
    assertEquals("http://gateway.marvel.com/v1/public/comics/42882/events",
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