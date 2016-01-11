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

package com.karumi.marvelapiclient.model;

import com.karumi.marvelapiclient.utils.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeriesQuery {
  private static final String QUERY_TITLE = "title";
  private static final String QUERY_TITLE_STARTS_WITH = "titleStartsWith";
  private static final String QUERY_START_YEAR = "startYear";
  private static final String QUERY_MODIFIED_SINCE = "modifiedSince";
  private static final String QUERY_CREATORS = "creators";
  private static final String QUERY_CHARACTERS = "characters";
  private static final String QUERY_COMICS = "comics";
  private static final String QUERY_EVENTS = "events";
  private static final String QUERY_STORIES = "stories";
  private static final String QUERY_ORDER_BY = "orderBy";
  private static final String QUERY_LIMIT = "limit";
  private static final String QUERY_OFFSET = "offset";
  private static final String QUERY_SERIES_TYPE = "seriesType";
  private static final String QUERY_CONTAINS = "contains";

  private String title;
  private String titleStartsWith;
  private int startYear;
  private String modifiedSince;
  private String comics;
  private String stories;
  private String events;
  private String creators;
  private String characters;
  private String orderBy;
  private String seriesType;
  private String contains;
  private int limit;
  private int offset;

  public SeriesQuery(String title, String titleStartsWith, int startYear, String modifiedSince,
      String comics, String stories, String events, String creators, String characters,
      String orderBy, String seriesType, String contains, int limit, int offset) {
    this.title = title;
    this.titleStartsWith = titleStartsWith;
    this.startYear = startYear;
    this.modifiedSince = modifiedSince;
    this.comics = comics;
    this.stories = stories;
    this.events = events;
    this.creators = creators;
    this.characters = characters;
    this.orderBy = orderBy;
    this.seriesType = seriesType;
    this.contains = contains;
    this.limit = limit;
    this.offset = offset;
  }

  public Map<String, Object> toMap() {
    Map<String, Object> returnValues = new HashMap<>();

    if (title != null) {
      returnValues.put(QUERY_TITLE, title);
    }
    if (titleStartsWith != null) {
      returnValues.put(QUERY_TITLE_STARTS_WITH, titleStartsWith);
    }

    if (startYear > 0) {
      returnValues.put(QUERY_START_YEAR, startYear);
    }

    if (modifiedSince != null) {
      returnValues.put(QUERY_MODIFIED_SINCE, modifiedSince);
    }

    if (creators != null) {
      returnValues.put(QUERY_CREATORS, creators);
    }

    if (characters != null) {
      returnValues.put(QUERY_CHARACTERS, characters);
    }

    if (comics != null) {
      returnValues.put(QUERY_COMICS, comics);
    }

    if (events != null) {
      returnValues.put(QUERY_EVENTS, events);
    }

    if (stories != null) {
      returnValues.put(QUERY_STORIES, stories);
    }

    if (seriesType != null) {
      returnValues.put(QUERY_SERIES_TYPE, seriesType);
    }

    if (contains != null) {
      returnValues.put(QUERY_CONTAINS, contains);
    }

    if (orderBy != null) {
      returnValues.put(QUERY_ORDER_BY, orderBy);
    }

    if (limit > 0) {
      returnValues.put(QUERY_LIMIT, limit);
    }

    if (offset > 0) {
      returnValues.put(QUERY_OFFSET, offset);
    }

    return returnValues;
  }

  public enum SeriesType {
    COLLECTION("collection"),
    ONE_SHOT("oneShot"),
    LIMITED("limited"),
    ONGOING("ongoing");

    private final String seriesType;

    private SeriesType(final String seriesType) {
      this.seriesType = seriesType;
    }

    @Override public String toString() {
      return seriesType;
    }
  }

  public static class Builder {
    private static final String LIST_SEPARATOR = ",";
    public final static int MAX_SIZE = 100;

    private String title;
    private String titleStartsWith;
    private int startYear;
    private Date modifiedSince;
    private List<Integer> creators = new ArrayList<>();
    private List<Integer> characters = new ArrayList<>();
    private List<Integer> comics = new ArrayList<>();
    private List<Integer> events = new ArrayList<>();
    private List<Integer> stories = new ArrayList<>();
    private Format contains;
    private SeriesType seriesType;
    private OrderBy orderBy;
    private int limit;
    private int offset;
    private boolean orderByAscendant;

    private Builder() {
    }

    public static Builder create() {
      return new Builder();
    }

    public Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder withTitleStartsWith(String titleStartsWith) {
      this.titleStartsWith = titleStartsWith;
      return this;
    }

    public Builder withStartYear(int year) {
      this.startYear = year;
      return this;
    }

    public Builder withModifiedSince(Date modifiedSince) {
      this.modifiedSince = modifiedSince;
      return this;
    }

    public Builder addCreator(int creatorId) {
      creators.add(creatorId);
      return this;
    }

    public Builder addCreator(List<Integer> creatorIds) {
      checkNotNull(creatorIds);
      creators.addAll(creatorIds);
      return this;
    }

    public Builder addCharacter(int characterId) {
      characters.add(characterId);
      return this;
    }

    public Builder addCharacter(List<Integer> characterIds) {
      checkNotNull(characterIds);
      characters.addAll(characterIds);
      return this;
    }

    public Builder addComic(int comicId) {
      comics.add(comicId);
      return this;
    }

    public Builder addComic(List<Integer> comicIds) {
      checkNotNull(comicIds);
      comics.addAll(comicIds);
      return this;
    }

    public Builder addEvent(int eventId) {
      events.add(eventId);
      return this;
    }

    public Builder addEvent(List<Integer> eventIds) {
      checkNotNull(eventIds);
      events.addAll(eventIds);
      return this;
    }

    public Builder addStory(int storyId) {
      stories.add(storyId);
      return this;
    }

    public Builder addStory(List<Integer> storyIds) {
      checkNotNull(storyIds);
      stories.addAll(storyIds);
      return this;
    }

    public Builder withOrderBy(OrderBy orderBy, boolean ascendant) {
      this.orderBy = orderBy;
      this.orderByAscendant = ascendant;
      return this;
    }

    public Builder withSeriesType(SeriesType seriesType) {
      this.seriesType = seriesType;
      return this;
    }

    public Builder withContains(Format format) {
      this.contains = format;
      return this;
    }

    public Builder withOrderBy(OrderBy orderBy) {
      return withOrderBy(orderBy, true);
    }

    public Builder withLimit(int limit) {
      checkLimit(limit);
      this.limit = limit;
      return this;
    }

    public Builder withOffset(int offset) {
      this.offset = offset;
      return this;
    }

    public SeriesQuery build() {
      String modifiedSinceAsString = convertDate(modifiedSince);
      String creatorsAsString = convertToList(creators);
      String charactersAsString = convertToList(characters);
      String comicsAsString = convertToList(comics);
      String eventsAsString = convertToList(events);
      String storiesAsString = convertToList(stories);
      String orderByAsString = convertOrderBy(orderBy, orderByAscendant);
      String containsAsString = (contains != null) ? contains.toString() : null;
      String seriesTypeAsString = (seriesType != null) ? seriesType.toString() : null;

      return new SeriesQuery(title, titleStartsWith, startYear, modifiedSinceAsString,
          comicsAsString, storiesAsString, eventsAsString, creatorsAsString, charactersAsString,
          orderByAsString, seriesTypeAsString, containsAsString, limit, offset);
    }

    private void checkLimit(int limit) {
      if (limit <= 0) {
        throw new IllegalArgumentException("limit must be bigger than zero");
      }

      if (limit > MAX_SIZE) {
        throw new IllegalArgumentException("limit must be smaller than 100");
      }
    }

    private void checkNotNull(Object object) {
      if (object == null) {
        throw new IllegalArgumentException("the argument can not be null");
      }
    }

    private String convertDate(Date date) {
      if (date == null) {
        return null;
      }
      return DateUtil.parseDate(date);
    }

    private String convertOrderBy(OrderBy orderBy, boolean ascendant) {
      if (orderBy == null) {
        return null;
      }

      String plainOrderBy = orderBy.toString();
      return (ascendant) ? plainOrderBy : "-" + plainOrderBy;
    }

    private String convertToList(List<Integer> list) {
      String plainList = "";
      for (int i = 0; i < list.size(); i++) {
        plainList += Integer.toString(list.get(i));
        if (i < list.size() - 1) {
          plainList += LIST_SEPARATOR;
        }
      }
      return (plainList.isEmpty()) ? null : plainList;
    }
  }
}
