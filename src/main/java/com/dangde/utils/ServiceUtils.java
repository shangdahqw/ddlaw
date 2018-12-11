package com.dangde.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class ServiceUtils {

  public static String md5(String message) {
    try {
      MessageDigest md = MessageDigest.getInstance("md5");
      byte md5[] = md.digest(message.getBytes());
      Encoder encoder = Base64.getEncoder();
      return encoder.encodeToString(md5);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
