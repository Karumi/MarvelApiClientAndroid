package com.karumi.marvelapiclient.model;

import com.google.gson.annotations.SerializedName;

public class StoryResource extends MarvelResource {
  @SerializedName("type") private String type;

  public String getType() {
    return type;
  }

  @Override public String toString() {
    return "StoryResource{"
           + "name="
           + super.getName()
           + "resourceUri="
           + super.getResourceUri()
           + "type='"
           + type
           + '\''
           +
           '}';
  }
}
