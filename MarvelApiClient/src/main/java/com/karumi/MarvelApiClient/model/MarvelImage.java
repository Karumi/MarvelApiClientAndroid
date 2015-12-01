package com.karumi.marvelapiclient.model;

import com.google.gson.annotations.SerializedName;

public class MarvelImage {
  @SerializedName("path") private String path;
  @SerializedName("extension") private String extension;

  public String getPath() {
    return path;
  }

  public String getExtension() {
    return extension;
  }

  @Override public String toString() {
    return "MarvelImage{"
           + "path='" + path + '\''
           + ", extension='" + extension
           + '\'' + '}';
  }
}
