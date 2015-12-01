package com.karumi.marvelapiclient.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
  public static String parseDate(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    return format.format(date);
  }
}
