package com.karumi.marvelapiclient.model;

import com.google.gson.annotations.SerializedName;

public class MarvelResponse<T> {
  @SerializedName("code") private int code;
  @SerializedName("status") private String status;
  @SerializedName("copyright") private String copyright;
  @SerializedName("attributionText") private String attributionText;
  @SerializedName("attributionHTML") private String getAttributionHtml;

  @SerializedName("data") private T response;

  public int getCode() {
    return code;
  }

  public String getStatus() {
    return status;
  }

  public String getCopyright() {
    return copyright;
  }

  public String getAttributionText() {
    return attributionText;
  }

  public String getGetAttributionHtml() {
    return getAttributionHtml;
  }

  public T getResponse() {
    return response;
  }

  @Override public String toString() {
    return "MarvelResponse{"
           + "code="
           + code
           + ", status='"
           + status
           + '\''
           + ", copyright='"
           + copyright
           + '\''
           + ", attributionText='"
           + attributionText
           + '\''
           + ", getAttributionHtml='"
           + getAttributionHtml
           + '\''
           + ", response="
           + response
           +
           '}';
  }
}
