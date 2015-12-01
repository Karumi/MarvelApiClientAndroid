package com.karumi.marvelapiclient.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CharacterDto {

  @SerializedName("id") private String id;
  @SerializedName("name") private String name;
  @SerializedName("description") private String description;
  @SerializedName("modified") private String modified;
  @SerializedName("resourceURI") private String resourceUri;
  @SerializedName("urls") private List<MarvelUrl> urls;
  @SerializedName("thumbnail") private MarvelImage thumbnail;
  @SerializedName("comics") private MarvelResources<ComicResource> comics;
  @SerializedName("stories") private MarvelResources<StoryResource> stories;
  @SerializedName("events") private MarvelResources<EventResource> events;
  @SerializedName("series") private MarvelResources<SerieResource> series;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getModified() {
    return modified;
  }

  public String getResourceUri() {
    return resourceUri;
  }

  public List<MarvelUrl> getUrls() {
    return urls;
  }

  public MarvelImage getThumbnail() {
    return thumbnail;
  }

  public MarvelResources<ComicResource> getComics() {
    return comics;
  }

  public MarvelResources<StoryResource> getStories() {
    return stories;
  }

  public MarvelResources<EventResource> getEvents() {
    return events;
  }

  public MarvelResources<SerieResource> getSeries() {
    return series;
  }

  @Override public String toString() {
    return "CharacterDto{"
           + "id='"
           + id
           + '\''
           + ", name='"
           + name
           + '\''
           + ", description='"
           + description
           + '\''
           + ", modified='"
           + modified
           + '\''
           + ", resourceUri='"
           + resourceUri
           + '\''
           + ", urls="
           + urls
           + ", thumbnail="
           + thumbnail
           + ", comics="
           + comics
           + ", stories="
           + stories
           + ", events="
           + events
           + ", series="
           + series
           +
           '}';
  }
}
