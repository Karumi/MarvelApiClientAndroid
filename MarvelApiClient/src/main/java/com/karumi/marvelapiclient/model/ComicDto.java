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

public class ComicDto {
  @SerializedName("id") private String id;
  @SerializedName("digitalId") private int digitalId;
  @SerializedName("title") private String title;
  @SerializedName("issueNumber") private double issueNumber;
  @SerializedName("variantDescription") private String variantDescription;
  @SerializedName("description") private String description;
  @SerializedName("modified") private String modified;
  @SerializedName("isbn") private String isbn;
  @SerializedName("upc") private String upc;
  @SerializedName("diamondCode") private String diamondCode;
  @SerializedName("ean") private String ean;
  @SerializedName("issn") private String issn;
  @SerializedName("format") private String format;
  @SerializedName("pageCount") private int pageCount;
  @SerializedName("textObjects") private List<TextObject> textObjects;
  @SerializedName("resourceURI") private String resourceURI;
  @SerializedName("urls") private List<MarvelUrl> urls;
  @SerializedName("series") private ComicResourceDto series;
  @SerializedName("variants") private List<MarvelResourceDto> variants;
  @SerializedName("collections") private List<MarvelCollection> collections;
  @SerializedName("collectedIssues") private List<MarvelCollection> collectedIssues;
  @SerializedName("dates") private List<MarvelDate> dates;
  @SerializedName("prices") private List<MarvelPrice> prices;
  @SerializedName("thumbnail") private MarvelImage thumbnail;
  @SerializedName("images") private List<MarvelImage> images;
  @SerializedName("creators") private MarvelResources<CreatorResourceDto> creators;
  @SerializedName("characters") private MarvelResources<CharacterResourceDto> characters;
  @SerializedName("stories") private MarvelResources<StoryResourceDto> stories;
  @SerializedName("events") private MarvelResources<EventResourceDto> events;

  public String getId() {
    return id;
  }

  public int getDigitalId() {
    return digitalId;
  }

  public String getTitle() {
    return title;
  }

  public double getIssueNumber() {
    return issueNumber;
  }

  public String getVariantDescription() {
    return variantDescription;
  }

  public String getDescription() {
    return description;
  }

  public String getModified() {
    return modified;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getUpc() {
    return upc;
  }

  public String getDiamondCode() {
    return diamondCode;
  }

  public String getEan() {
    return ean;
  }

  public String getIssn() {
    return issn;
  }

  public String getFormat() {
    return format;
  }

  public int getPageCount() {
    return pageCount;
  }

  public List<TextObject> getTextObjects() {
    return textObjects;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public List<MarvelUrl> getUrls() {
    return urls;
  }

  public ComicResourceDto getSeries() {
    return series;
  }

  public List<MarvelResourceDto> getVariants() {
    return variants;
  }

  public List<MarvelCollection> getCollections() {
    return collections;
  }

  public List<MarvelCollection> getCollectedIssues() {
    return collectedIssues;
  }

  public List<MarvelDate> getDates() {
    return dates;
  }

  public List<MarvelPrice> getPrices() {
    return prices;
  }

  public MarvelImage getThumbnail() {
    return thumbnail;
  }

  public List<MarvelImage> getImages() {
    return images;
  }

  public MarvelResources<CreatorResourceDto> getCreators() {
    return creators;
  }

  public MarvelResources<CharacterResourceDto> getCharacters() {
    return characters;
  }

  public MarvelResources<StoryResourceDto> getStories() {
    return stories;
  }

  public MarvelResources<EventResourceDto> getEvents() {
    return events;
  }

  @Override public String toString() {
    return "ComicDto{" +
        "id='" + id + '\'' +
        ", digitalId=" + digitalId +
        ", title='" + title + '\'' +
        ", issueNumber=" + issueNumber +
        ", variantDescription='" + variantDescription + '\'' +
        ", description='" + description + '\'' +
        ", modified='" + modified + '\'' +
        ", isbn='" + isbn + '\'' +
        ", upc='" + upc + '\'' +
        ", diamondCode='" + diamondCode + '\'' +
        ", ean='" + ean + '\'' +
        ", issn='" + issn + '\'' +
        ", format='" + format + '\'' +
        ", pageCount=" + pageCount +
        ", textObjects=" + textObjects +
        ", resourceURI='" + resourceURI + '\'' +
        ", urls=" + urls +
        ", series=" + series +
        ", variants=" + variants +
        ", collections=" + collections +
        ", collectedIssues=" + collectedIssues +
        ", dates=" + dates +
        ", prices=" + prices +
        ", thumbnail=" + thumbnail +
        ", images=" + images +
        ", creators=" + creators +
        ", characters=" + characters +
        ", stories=" + stories +
        ", events=" + events +
        '}';
  }
}
