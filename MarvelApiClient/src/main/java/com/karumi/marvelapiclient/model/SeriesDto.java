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

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SeriesDto {
  @SerializedName("id") private String id;
  @SerializedName("title") private String title;
  @SerializedName("description") private String description;
  @SerializedName("resourceURI") private String resourceURI;
  @SerializedName("urls") private List<MarvelUrl> urls;
  @SerializedName("startYear") private int startYear;
  @SerializedName("endYear") private int endYear;
  @SerializedName("rating") private String rating;
  @SerializedName("modified") private String modified;
  @SerializedName("thumbnail") private MarvelImage thumbnail;
  @SerializedName("comics") private MarvelResources<ComicResourceDto> comics;
  @SerializedName("stories") private MarvelResources<StoryResourceDto> stories;
  @SerializedName("events") private MarvelResources<EventResourceDto> events;
  @SerializedName("characters") private MarvelResources<CharacterResourceDto> characters;
  @SerializedName("creators") private MarvelResources<CreatorResourceDto> creators;
  @SerializedName("next") private MarvelResourceDto next;
  @SerializedName("previous") private MarvelResourceDto previous;

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public List<MarvelUrl> getUrls() {
    return urls;
  }

  public int getStartYear() {
    return startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public String getRating() {
    return rating;
  }

  public String getModified() {
    return modified;
  }

  public MarvelImage getThumbnail() {
    return thumbnail;
  }

  public MarvelResources<ComicResourceDto> getComics() {
    return comics;
  }

  public MarvelResources<StoryResourceDto> getStories() {
    return stories;
  }

  public MarvelResources<EventResourceDto> getEvents() {
    return events;
  }

  public MarvelResources<CharacterResourceDto> getCharacters() {
    return characters;
  }

  public MarvelResources<CreatorResourceDto> getCreators() {
    return creators;
  }

  public MarvelResourceDto getNext() {
    return next;
  }

  public MarvelResourceDto getPrevious() {
    return previous;
  }

  @Override public String toString() {
    return "SeriesDto{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", resourceURI='" + resourceURI + '\'' +
        ", urls=" + urls +
        ", startYear=" + startYear +
        ", endYear=" + endYear +
        ", rating='" + rating + '\'' +
        ", modified='" + modified + '\'' +
        ", thumbnail=" + thumbnail +
        ", comics=" + comics +
        ", stories=" + stories +
        ", events=" + events +
        ", characters=" + characters +
        ", creators=" + creators +
        ", next=" + next +
        ", previous=" + previous +
        '}';
  }
}