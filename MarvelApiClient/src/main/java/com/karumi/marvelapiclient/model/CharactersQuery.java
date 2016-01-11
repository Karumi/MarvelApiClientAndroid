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

public class CharactersQuery {

  private static final String QUERY_NAME = "name";
  private static final String QUERY_NAME_START_WITH = "nameStartsWith";
  private static final String QUERY_MODIFIED_SINCE = "modifiedSince";
  private static final String QUERY_COMICS = "comics";
  private static final String QUERY_SERIES = "series";
  private static final String QUERY_EVENTS = "events";
  private static final String QUERY_STORIES = "stories";
  private static final String QUERY_ORDER_BY = "orderBy";
  private static final String QUERY_LIMIT = "limit";
  private static final String QUERY_OFFSET = "offset";

  private String name;
  private String nameStartWith;
  private String modifiedSince;
  private String comics;
  private String series;
  private String events;
  private String stories;
  private String orderBy;
  private int limit;
  private int offset;

  private CharactersQuery(String name, String nameStartWith, String modifiedSince, String comics,
      String series, String events, String stories, String orderBy, int limit, int offset) {
    this.name = name;
    this.nameStartWith = nameStartWith;
    this.modifiedSince = modifiedSince;
    this.comics = comics;
    this.series = series;
    this.events = events;
    this.stories = stories;
    this.orderBy = orderBy;
    this.limit = limit;
    this.offset = offset;
  }

  public Map<String, Object> toMap() {
    Map<String, Object> returnValues = new HashMap<>();

    if (name != null) {
      returnValues.put(CharactersQuery.QUERY_NAME, name);
    }

    if (nameStartWith != null) {
      returnValues.put(CharactersQuery.QUERY_NAME_START_WITH, nameStartWith);
    }

    if (modifiedSince != null) {
      returnValues.put(CharactersQuery.QUERY_MODIFIED_SINCE, modifiedSince);
    }

    if (comics != null) {
      returnValues.put(CharactersQuery.QUERY_COMICS, comics);
    }

    if (series != null) {
      returnValues.put(CharactersQuery.QUERY_SERIES, series);
    }

    if (events != null) {
      returnValues.put(CharactersQuery.QUERY_EVENTS, events);
    }

    if (stories != null) {
      returnValues.put(CharactersQuery.QUERY_STORIES, stories);
    }

    if (orderBy != null) {
      returnValues.put(CharactersQuery.QUERY_ORDER_BY, orderBy);
    }

    if (limit > 0) {
      returnValues.put(CharactersQuery.QUERY_LIMIT, limit);
    }

    if (offset > 0) {
      returnValues.put(CharactersQuery.QUERY_OFFSET, offset);
    }

    return returnValues;
  }


  public static class Builder {
    public final static int MAX_SIZE = 100;

    public static final String LIST_SEPARATOR = ",";
    private String name;
    private String nameStartWith;
    private Date modifiedSince;
    private List<Integer> comics = new ArrayList<>();
    private List<Integer> series = new ArrayList<>();
    private List<Integer> events = new ArrayList<>();
    private List<Integer> stories = new ArrayList<>();
    private OrderBy orderBy;
    private boolean orderByAscendant;
    private int limit;
    private int offset;

    private Builder() {
    }

    public static Builder create() {
      return new Builder();
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withNameStartWith(String nameStartWith) {
      this.nameStartWith = nameStartWith;
      return this;
    }

    public Builder withModifiedSince(Date modifiedSince) {
      this.modifiedSince = modifiedSince;
      return this;
    }

    public Builder addComic(int comic) {
      comics.add(comic);
      return this;
    }

    public Builder addComics(List<Integer> comics) {
      checkNull(comics);
      this.comics.addAll(comics);
      return this;
    }

    public Builder addSerie(int serie) {
      series.add(serie);
      return this;
    }

    public Builder addSeries(List<Integer> series) {
      checkNull(series);
      this.series.addAll(series);
      return this;
    }

    public Builder addEvent(int event) {
      events.add(event);
      return this;
    }

    public Builder addEvents(List<Integer> events) {
      checkNull(events);
      this.events.addAll(events);
      return this;
    }

    public Builder addStory(int story) {
      stories.add(story);
      return this;
    }

    public Builder addStory(List<Integer> stories) {
      checkNull(stories);
      this.stories.addAll(stories);
      return this;
    }

    public Builder withOrderBy(OrderBy orderBy, boolean ascendant) {
      this.orderBy = orderBy;
      this.orderByAscendant = ascendant;
      return this;
    }

    public Builder withOrderBy(OrderBy orderBy) {
      this.orderBy = orderBy;
      this.orderByAscendant = true;
      return this;
    }

    public Builder withLimit(int limit) {
      checkLimit(limit);
      this.limit = limit;
      return this;
    }

    public Builder withOffset(int offset) {
      if (offset < 0) {
        throw new IllegalArgumentException("offset must be bigger or equals than zero");
      }

      this.offset = offset;
      return this;
    }

    public CharactersQuery build() {
      String plainModifedSince = convertDate(modifiedSince);
      String plainComics = convertToList(comics);
      String plainEvents = convertToList(events);
      String plainSeries = convertToList(series);
      String plainStories = convertToList(stories);
      String plainOrderBy = convertOrderBy(orderBy, orderByAscendant);

      return new CharactersQuery(name, nameStartWith, plainModifedSince, plainComics, plainSeries,
          plainEvents, plainStories, plainOrderBy, limit, offset);
    }

    private void checkLimit(int limit) {
      if (limit <= 0) {
        throw new IllegalArgumentException("limit must be bigger than zero");
      }

      if (limit > MAX_SIZE) {
        throw new IllegalArgumentException("limit must be smaller than 100");
      }
    }

    private void checkNull(List<Integer> list) {
      if (list == null) {
        throw new IllegalArgumentException("the collection can not be null");
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
