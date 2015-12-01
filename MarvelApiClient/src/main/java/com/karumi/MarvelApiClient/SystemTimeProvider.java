package com.karumi.marvelapiclient;

public class SystemTimeProvider implements TimeProvider {
  @Override public long currentTimeMillis() {
    return System.currentTimeMillis();
  }
}
