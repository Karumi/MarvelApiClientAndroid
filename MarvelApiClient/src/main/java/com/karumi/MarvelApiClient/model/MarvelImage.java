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

public class MarvelImage {
  private static final String SEPARATOR = "/";
  private static final String DOT = ".";
  @SerializedName("path") private String path;
  @SerializedName("extension") private String extension;

  public String getPath() {
    return path;
  }

  public String getExtension() {
    return extension;
  }

  public String getImageUrl(Size size) {
    if (size == Size.FULLSIZE) {
      return path + DOT + extension;
    } else {
      return path + SEPARATOR + size.toString() + DOT + extension;
    }
  }

  @Override public String toString() {
    return "MarvelImage{" + "path='" + path + '\'' + ", extension='" + extension + '\'' + '}';
  }

  public enum Size {
    /** 50x75px */
    PORTRAIT_SMALL("portrait_small"),
    /** 100x150px */
    PORTRAIT_MEDIUM("portrait_medium"),
    /** 150x225px */
    PORTRAIT_XLARGE("portrait_xlarge"),
    /** 168x252px */
    PORTRAIT_FANTASTIC("portrait_fantastic"),
    /** 300x450px */
    PORTRAIT_UNCANNY("portrait_uncanny"),
    /** 216x324px */
    PORTRAIT_INCREDIBLE("portrait_incredible"),
    /** 65x45px */
    STANDARD_SMALL("standard_small"),
    /** 100x100px */
    STANDARD_MEDIUM("standard_medium"),
    /** 140x140px */
    STANDARD_LARGE("standard_large"),
    /** 200x200px */
    STANDARD_XLARGE("standard_xlarge"),
    /** 250x250px */
    STANDARD_FANTASTIC("standard_fantastic"),
    /** 180x180px */
    STANDARD_AMAZING("standard_amazing"),
    /** 120x90px */
    LANDSCAPE_SMALL("landscape_small"),
    /** 175x130px */
    LANDSCAPE_MEDIUM("landscape_medium"),
    /** 190x140px */
    LANDSCAPE_LARGE("landscape_large"),
    /** 270x200px */
    LANDSCAPE_XLARGE("landscape_xlarge"),
    /** 250x156px */
    LANDSCAPE_AMAZING("landscape_amazing"),
    /** 464x261px */
    LANDSCAPE_INCREDIBLE("landscape_incredible"),
    DETAIL("detail"),
    FULLSIZE("fullsize");

    private final String size;

    private Size(final String size) {
      this.size = size;
    }

    @Override public String toString() {
      return size;
    }
  }
}
