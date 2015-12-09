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

public class MarvelResources<T> {
  @SerializedName("available") private int available;
  @SerializedName("returned") private int returned;
  @SerializedName("collectionURI") private String collectionUri;
  @SerializedName("items") private List<T> items;

  public int getAvailable() {
    return available;
  }

  public int getReturned() {
    return returned;
  }

  public String getCollectionUri() {
    return collectionUri;
  }

  public List<T> getItems() {
    return items;
  }

  @Override public String toString() {
    return "MarvelResources{"
           + "available="
           + available
           + ", returned="
           + returned
           + ", collectionUri='"
           + collectionUri
           + '\''
           + ", items="
           + items
           +
           '}';
  }
}
