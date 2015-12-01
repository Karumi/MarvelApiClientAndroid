package com.karumi.marvelapiclient.model;

import com.google.gson.annotations.SerializedName;

public class MarvelResource {
  @SerializedName("resourceURI") private String resourceUri;
  @SerializedName("name") private String name;

  public String getResourceUri() {
    return resourceUri;
  }

  public String getName() {
    return name;
  }

  @Override public String toString() {
    return "MarvelResource{"
           + "resourceUri='"
           + resourceUri
           + '\''
           + ", name='"
           + name
           + '\''
           + '}';
  }
}
