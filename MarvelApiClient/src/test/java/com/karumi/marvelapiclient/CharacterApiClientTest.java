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
import com.karumi.marvelapiclient.model.ComicResourceDto;
import com.karumi.marvelapiclient.model.MarvelImage;
import com.karumi.marvelapiclient.model.MarvelResources;
import com.karumi.marvelapiclient.model.MarvelResponse;
import com.karumi.marvelapiclient.model.MarvelUrl;
import com.karumi.marvelapiclient.model.OrderBy;
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

public class CharacterApiClientTest extends ApiClientTest {

  private static final String ANY_PUBLIC_KEY = "1234";
  private static final String ANY_PRIVATE_KEY = "abcd";
  private static final String ANY_URL = "http://fake.marvel.com/";
  private static final int ANY_OFFSET = 1;
  private static final int ANY_LIMIT = 10;
  private static final int INVALID_LIMIT = 0;
  private static final String ANY_NAME = "Spider-man";
  private static final String ANY_START_NAME = "Spider";
  private static final int ANY_COMIC_1 = 1;
  private static final int ANY_COMIC_2 = 2;
  private static final int ANY_SERIE = 1;
  private static final int ANY_STORY = 1;
  private static final int ANY_EVENT_1 = 1;
  private static final int ANY_EVENT_2 = 2;
  private static final String ANY_MODIFIED_SINCE = "2015-01-09T22:10:45-0800";
  private static final String ORDER_NAME_DESCEDANT_VALUE = "-name";
  private static final String INVALID_CHARACTER_ID = "";
  private static final String ANY_NOT_FOUND_ID = "1234";
  private static final String THREED_MAN_CHARACTER_ID = "123456";
  public static final String COMICS_REQUEST = "1,2";
  public static final String EVENTS_REQUEST = "1,2";

  @Test public void shouldRequestCharactersByOffsetAndLimitWithAsParams() throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();
    enqueueMockResponse();

    characterApiClient.getAll(ANY_OFFSET, ANY_LIMIT);

    assertRequestSentToContains("offset=" + ANY_OFFSET, "limit=" + ANY_LIMIT);
  }

  @Test(expected = IllegalArgumentException.class) public void shouldThrowExceptionWhenLimitIsZero()
      throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();
    enqueueMockResponse();

    characterApiClient.getAll(ANY_OFFSET, INVALID_LIMIT);
  }

  @Test public void shouldRequestCharactersUsingAllTheRequestParams() throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();
    enqueueMockResponse();

    CharactersQuery query = CharactersQuery.Builder.create()
        .withName(ANY_NAME)
        .withNameStartWith(ANY_START_NAME)
        .withLimit(ANY_LIMIT)
        .withOffset(ANY_OFFSET)
        .withModifiedSince(getAnyDate())
        .withOrderBy(OrderBy.NAME, false)
        .addComic(ANY_COMIC_1)
        .addComic(ANY_COMIC_2)
        .addEvents(getAnyEvents())
        .addSerie(ANY_SERIE)
        .addStory(ANY_STORY)
        .build();
    characterApiClient.getAll(query);

    assertRequestSentToContains("offset=" + ANY_OFFSET, "limit=" + ANY_LIMIT, "name=" + ANY_NAME,
        "nameStartsWith=" + ANY_START_NAME, "modifiedSince=" + ANY_MODIFIED_SINCE,
        "orderBy=" + ORDER_NAME_DESCEDANT_VALUE, "comics=" + COMICS_REQUEST,
        "events=" + EVENTS_REQUEST, "series=" + ANY_SERIE, "stories=" + ANY_STORY);
  }

  @Test(expected = MarvelAuthApiException.class)
  public void shouldThrowMarvelApiExceptionOnAuthHttpError() throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();
    enqueueMockResponse(401,
        "{\"code\":\"InvalidCredentials\",\"message\":\"That hash, timestamp and key "
        + "combination is invalid.\"}");

    try {
      characterApiClient.getAll(ANY_OFFSET, ANY_LIMIT);
    } catch (MarvelApiException e) {
      assertEquals("InvalidCredentials", e.getMarvelCode());
      assertEquals("That hash, timestamp and key combination is invalid.", e.getMessage());
      throw e;
    }
  }

  @Test public void shouldParseGetAllCharactersResponse() throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();
    enqueueMockResponse("getCharacters.json");

    MarvelResponse<CharactersDto> characters = characterApiClient.getAll(0, ANY_LIMIT);

    assertBasicMarvelResponse(characters);
    assertGetAllCharactersResponseIsProperlyParsed(characters);
  }

  @Test(expected = IllegalArgumentException.class) public void shouldNotAcceptEmptyCharacterIds()
      throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();

    characterApiClient.getCharacter(INVALID_CHARACTER_ID);
  }

  @Test public void shouldSendGetCharacterRequestToTheCorrectEndpoint() throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();
    enqueueMockResponse("getCharacter.json");

    characterApiClient.getCharacter(THREED_MAN_CHARACTER_ID);

    assertRequestSentToContains("characters/" + THREED_MAN_CHARACTER_ID);
  }

  @Test(expected = MarvelApiException.class)
  public void shouldReturnMarvelExceptionWhenTheIdDoesNotExist() throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();
    enqueueMockResponse(404, "{\"code\":404,\"status\":\"We couldn't find that character\"}");

    try {
      characterApiClient.getCharacter(ANY_NOT_FOUND_ID);
    } catch (MarvelApiException e) {
      assertEquals("404", e.getMarvelCode());
      assertEquals("We couldn't find that character", e.getMessage());
      throw e;
    }
  }

  @Test public void shouldParseGetCharacterResponse() throws Exception {
    CharacterApiClient characterApiClient = givenCharacterApiClient();
    enqueueMockResponse("getCharacter.json");

    MarvelResponse<CharacterDto> character =
        characterApiClient.getCharacter(THREED_MAN_CHARACTER_ID);

    assertBasicMarvelResponse(character);

    CharacterDto response = character.getResponse();
    assertIs3dManCharacter(response);
  }

  private List<Integer> getAnyEvents() {
    List<Integer> events = new ArrayList<>();
    events.add(ANY_EVENT_1);
    events.add(ANY_EVENT_2);
    return events;
  }

  private CharacterApiClient givenCharacterApiClient() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(getBaseEndpoint())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    MarvelApiConfig marvelApiConfig =
        new MarvelApiConfig.Builder(ANY_PUBLIC_KEY, ANY_PRIVATE_KEY).baseUrl(ANY_URL)
            .retrofit(retrofit)
            .build();
    return new CharacterApiClient(marvelApiConfig);
  }

  private void assertGetAllCharactersResponseIsProperlyParsed(
      MarvelResponse<CharactersDto> characters) {
    CharactersDto charactersDto = characters.getResponse();
    assertEquals(10, charactersDto.getCount());
    assertEquals(10, charactersDto.getLimit());
    assertEquals(0, charactersDto.getOffset());
    assertEquals(1485, charactersDto.getTotal());

    CharacterDto firstCharacter = charactersDto.getCharacters().get(0);
    assertIs3dManCharacter(firstCharacter);
  }

  private void assertIs3dManCharacter(CharacterDto character) {
    assertEquals("1011334", character.getId());
    assertEquals("3-D Man", character.getName());
    assertEquals("3-D man is a 3d superhero", character.getDescription());
    assertEquals("2014-04-29T14:18:17-0400", character.getModified());
    assertEquals("http://gateway.marvel.com/v1/public/characters/1011334",
        character.getResourceUri());

    MarvelImage thumbnail = character.getThumbnail();
    assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784", thumbnail.getPath());
    assertEquals("jpg", thumbnail.getExtension());

    List<MarvelUrl> urls = character.getUrls();
    assertEquals(3, urls.size());
    MarvelUrl marvelUrl = urls.get(0);
    assertEquals("detail", marvelUrl.getType());
    assertEquals("http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source="
                 + "838a08a2f4c39fa3fd218b1b2d43f19a", marvelUrl.getUrl());

    MarvelResources<ComicResourceDto> comics = character.getComics();
    assertEquals(11, comics.getAvailable());
    assertEquals(11, comics.getReturned());
    assertEquals("http://gateway.marvel.com/v1/public/characters/1011334/comics",
        comics.getCollectionUri());
    assertEquals(11, comics.getItems().size());
    ComicResourceDto firstComic = comics.getItems().get(0);
    assertEquals("Avengers: The Initiative (2007) #14", firstComic.getName());
    assertEquals("http://gateway.marvel.com/v1/public/comics/21366", firstComic.getResourceUri());

    MarvelResources<StoryResourceDto> stories = character.getStories();
    assertEquals(17, stories.getAvailable());
    assertEquals(17, stories.getReturned());
    assertEquals("http://gateway.marvel.com/v1/public/characters/1011334/stories",
        stories.getCollectionUri());
    assertEquals(17, stories.getItems().size());
    StoryResourceDto firstStory = stories.getItems().get(0);
    assertEquals("Cover #19947", firstStory.getName());
    assertEquals("http://gateway.marvel.com/v1/public/stories/19947", firstStory.getResourceUri());
    assertEquals("cover", firstStory.getType());
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
