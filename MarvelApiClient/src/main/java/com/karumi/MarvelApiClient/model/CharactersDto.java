package com.karumi.marvelapiclient.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class CharactersDto {
  @SerializedName("offset") private int offset;
  @SerializedName("limit") private int limit;
  @SerializedName("total") private int total;
  @SerializedName("count") private int count;
  @SerializedName("results") private List<CharacterDto> characters = new ArrayList<>();

  public int getOffset() {
    return offset;
  }

  public int getLimit() {
    return limit;
  }

  public int getTotal() {
    return total;
  }

  public int getCount() {
    return count;
  }

  public List<CharacterDto> getCharacters() {
    return characters;
  }

  @Override public String toString() {
    return "CharactersDto{"
           + "offset="
           + offset
           + ", limit="
           + limit
           + ", total="
           + total
           + ", count="
           + count
           + ", characters="
           + characters.toString()
           + '}';
  }
}
