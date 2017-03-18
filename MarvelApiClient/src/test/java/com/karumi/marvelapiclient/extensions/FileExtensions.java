package com.karumi.marvelapiclient.extensions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * Used to extend file functionality with new methods like the one to read from a file and parse
 * it to a string.
 */
public final class FileExtensions {

  private static final String UTF_8 = "UTF-8";

  private FileExtensions() {
  }

  public static String getStringFromFile(Class clazz, String filePath) throws IOException {
    ClassLoader classLoader = clazz.getClassLoader();
    File file = new File(classLoader.getResource("resources/" + filePath).getFile());
    return FileUtils.readFileToString(file, UTF_8);
  }
}
