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

public class ComicsQuery {
  private static final String QUERY_FORMAT = "format";
  private static final String QUERY_FORMAT_TYPE = "formatType";
  private static final String QUERY_NO_VARIANTS = "noVariants";
  private static final String QUERY_DATE_DESCRIPTOR = "dateDescriptor";
  private static final String QUERY_DATE_RANGE = "dateRange";
  private static final String QUERY_TITLE = "title";
  private static final String QUERY_TITLE_STARTS_WITH = "titleStartsWith";
  private static final String QUERY_START_YEAR = "startYear";
  private static final String QUERY_ISSUE_NUMBER = "issueNumber";
  private static final String QUERY_DIAMOND_CODE = "diamondCode";
  private static final String QUERY_DIGITAL_ID = "digitalId";
  private static final String QUERY_UPC = "upc";
  private static final String QUERY_ISBN = "isbn";
  private static final String QUERY_EAN = "ean";
  private static final String QUERY_ISSN = "issn";
  private static final String QUERY_HAS_DIGITAL_ISSUE = "hasDigitalIssue";
  private static final String QUERY_MODIFIED_SINCE = "modifiedSince";
  private static final String QUERY_CREATORS = "creators";
  private static final String QUERY_CHARACTERS = "characters";
  private static final String QUERY_SERIES = "series";
  private static final String QUERY_EVENTS = "events";
  private static final String QUERY_STORIES = "stories";
  private static final String QUERY_SHARED_APPEARANCES = "appearances";
  private static final String QUERY_COLLABORATORS = "collaborators";
  private static final String QUERY_ORDER_BY = "orderBy";
  private static final String QUERY_LIMIT = "limit";
  private static final String QUERY_OFFSET = "offset";

  private String format;
  private String formatType;
  private boolean noVariants;
  private String dateDescriptor;
  private String dateRange;
  private String title;
  private String titleStartsWith;
  private int startYear;
  private int issueNumber;
  private String diamondCode;
  private int digitalId;
  private String upc;
  private String isbn;
  private String ean;
  private String issn;
  private boolean hasDigitalIssue;
  private String modifiedSince;
  private String creators;
  private String characters;
  private String series;
  private String events;
  private String stories;
  private String sharedAppearances;
  private String collaborators;
  private String orderBy;
  private int limit;
  private int offset;

  ComicsQuery(String format, String formatType, boolean noVariants, String dateDescriptor,
      String dateRange, String title, String titleStartsWith, int startYear, int issueNumber,
      String diamondCode, int digitalId, String upc, String isbn, String ean, String issn,
      boolean hasDigitalIssue, String modifiedSince, String creators, String characters,
      String series, String events, String stories, String sharedAppearances, String collaborators,
      String orderBy, int limit, int offset) {
    this.format = format;
    this.formatType = formatType;
    this.noVariants = noVariants;
    this.dateDescriptor = dateDescriptor;
    this.dateRange = dateRange;
    this.title = title;
    this.titleStartsWith = titleStartsWith;
    this.startYear = startYear;
    this.issueNumber = issueNumber;
    this.diamondCode = diamondCode;
    this.digitalId = digitalId;
    this.upc = upc;
    this.isbn = isbn;
    this.ean = ean;
    this.issn = issn;
    this.hasDigitalIssue = hasDigitalIssue;
    this.modifiedSince = modifiedSince;
    this.creators = creators;
    this.characters = characters;
    this.series = series;
    this.events = events;
    this.stories = stories;
    this.sharedAppearances = sharedAppearances;
    this.collaborators = collaborators;
    this.orderBy = orderBy;
    this.limit = limit;
    this.offset = offset;
  }

  public Map<String, Object> toMap() {
    Map<String, Object> returnValues = new HashMap<>();

    if (format != null) {
      returnValues.put(QUERY_FORMAT, format);
    }

    if (formatType != null) {
      returnValues.put(QUERY_FORMAT_TYPE, formatType);
    }

    if (noVariants) {
      returnValues.put(QUERY_NO_VARIANTS, noVariants);
    }

    if (dateDescriptor != null) {
      returnValues.put(QUERY_DATE_DESCRIPTOR, dateDescriptor);
    }

    if (dateRange != null) {
      returnValues.put(QUERY_DATE_RANGE, dateRange);
    }
    if (title != null) {
      returnValues.put(QUERY_TITLE, title);
    }
    if (titleStartsWith != null) {
      returnValues.put(QUERY_TITLE_STARTS_WITH, titleStartsWith);
    }

    if (startYear > 0) {
      returnValues.put(QUERY_START_YEAR, startYear);
    }

    if (issueNumber > 0) {
      returnValues.put(QUERY_ISSUE_NUMBER, issueNumber);
    }

    if (diamondCode != null) {
      returnValues.put(QUERY_DIAMOND_CODE, diamondCode);
    }

    if (digitalId > 0) {
      returnValues.put(QUERY_DIGITAL_ID, digitalId);
    }

    if (upc != null) {
      returnValues.put(QUERY_UPC, upc);
    }

    if (isbn != null) {
      returnValues.put(QUERY_ISBN, isbn);
    }

    if (ean != null) {
      returnValues.put(QUERY_EAN, ean);
    }

    if (issn != null) {
      returnValues.put(QUERY_ISSN, issn);
    }

    if (hasDigitalIssue) {
      returnValues.put(QUERY_HAS_DIGITAL_ISSUE, hasDigitalIssue);
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

    if (series != null) {
      returnValues.put(QUERY_SERIES, series);
    }

    if (events != null) {
      returnValues.put(QUERY_EVENTS, events);
    }

    if (stories != null) {
      returnValues.put(QUERY_STORIES, stories);
    }

    if (sharedAppearances != null) {
      returnValues.put(QUERY_SHARED_APPEARANCES, sharedAppearances);
    }

    if (collaborators != null) {
      returnValues.put(QUERY_COLLABORATORS, collaborators);
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


  public enum FormatType {
    COMIC("comic"),
    COLLECTION("collection");

    private final String formatType;

    private FormatType(final String formatType) {
      this.formatType = formatType;
    }

    @Override public String toString() {
      return formatType;
    }
  }

  public enum DateDescriptor {
    LAST_WEEK("last week"),
    THIS_WEEK("this week"),
    NEXT_WEEK("next week"),
    THIS_MONTH("this month");

    private final String dateDescriptor;

    private DateDescriptor(final String dateDescriptor) {
      this.dateDescriptor = dateDescriptor;
    }

    @Override public String toString() {
      return dateDescriptor;
    }
  }

  public static class Builder {
    private static final String LIST_SEPARATOR = ",";
    public final static int MAX_SIZE = 100;

    private Format format;
    private FormatType formatType;
    private boolean noVariants;
    private DateDescriptor dateDescriptor;
    private Date dateRange;
    private String title;
    private String titleStartsWith;
    private int startYear;
    private int issueNumber;
    private String diamondCode;
    private int digitalId;
    private String upc;
    private String isbn;
    private String ean;
    private String issn;
    private boolean hasDigitalIssue;
    private Date modifiedSince;
    private List<Integer> creators = new ArrayList<>();
    private List<Integer> characters = new ArrayList<>();
    private List<Integer> series = new ArrayList<>();
    private List<Integer> events = new ArrayList<>();
    private List<Integer> stories = new ArrayList<>();
    private List<Integer> sharedAppearances = new ArrayList<>();
    private List<Integer> collaborators = new ArrayList<>();
    private OrderBy orderBy;
    private int limit;
    private int offset;
    private boolean orderByAscendant;

    private Builder() {
    }

    public static Builder create() {
      return new Builder();
    }

    public Builder withFormat(Format format) {
      this.format = format;
      return this;
    }

    public Builder withFormatType(FormatType formatType) {
      this.formatType = formatType;
      return this;
    }

    public Builder withNoVariants() {
      this.noVariants = true;
      return this;
    }

    public Builder withDateDescriptor(DateDescriptor dateDescriptor) {
      this.dateDescriptor = dateDescriptor;
      return this;
    }

    public Builder withDateRange(Date date) {
      checkNotNull(date);
      dateRange = date;
      return this;
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

    public Builder withIssueNumber(int issueNumber) {
      this.issueNumber = issueNumber;
      return this;
    }

    public Builder withDiamondCode(String diamondCode) {
      this.diamondCode = diamondCode;
      return this;
    }

    public Builder withDigitalId(int digitalId) {
      this.digitalId = digitalId;
      return this;
    }

    public Builder withUpc(String upc) {
      this.upc = upc;
      return this;
    }

    public Builder withIsbn(String isbn) {
      this.isbn = isbn;
      return this;
    }

    public Builder withEan(String ean) {
      this.ean = ean;
      return this;
    }

    public Builder withIssn(String issn) {
      this.issn = issn;
      return this;
    }

    public Builder withHasDigitalIssue() {
      hasDigitalIssue = true;
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

    public Builder addSerie(int serieId) {
      series.add(serieId);
      return this;
    }

    public Builder addSerie(List<Integer> serieIds) {
      checkNotNull(serieIds);
      series.addAll(serieIds);
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

    public Builder addSharedAppearance(int characterId) {
      sharedAppearances.add(characterId);
      return this;
    }

    public Builder addSharedAppearance(List<Integer> characterIds) {
      checkNotNull(characterIds);
      sharedAppearances.addAll(characterIds);
      return this;
    }

    public Builder addCollaborator(int creatorId) {
      collaborators.add(creatorId);
      return this;
    }

    public Builder addCollaborator(List<Integer> creatorIds) {
      checkNotNull(creatorIds);
      collaborators.addAll(creatorIds);
      return this;
    }

    public Builder withOrderBy(OrderBy orderBy, boolean ascendant) {
      this.orderBy = orderBy;
      this.orderByAscendant = ascendant;
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

    public ComicsQuery build() {
      String dataRangeAsString = convertDate(dateRange);
      String modifiedSinceAsString = convertDate(modifiedSince);
      String creatorsAsString = convertToList(creators);
      String charactersAsString = convertToList(characters);
      String seriesAsString = convertToList(series);
      String eventsAsString = convertToList(events);
      String storiesAsString = convertToList(stories);
      String sharedAppearancesAsString = convertToList(sharedAppearances);
      String collaboratorsAsString = convertToList(collaborators);
      String orderByAsString = convertOrderBy(orderBy, orderByAscendant);
      String formatAsString = (format != null) ? format.toString() : null;
      String formatTypeAsString = (formatType != null) ? formatType.toString() : null;
      String dateDescriptorAsString = (dateDescriptor != null) ? dateDescriptor.toString() : null;

      return new ComicsQuery(formatAsString, formatTypeAsString, noVariants,
          dateDescriptorAsString, dataRangeAsString, title, titleStartsWith, startYear,
          issueNumber, diamondCode, digitalId, upc, isbn, ean, issn, hasDigitalIssue,
          modifiedSinceAsString, creatorsAsString, charactersAsString, seriesAsString,
          eventsAsString, storiesAsString, sharedAppearancesAsString, collaboratorsAsString,
          orderByAsString, limit, offset);
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
