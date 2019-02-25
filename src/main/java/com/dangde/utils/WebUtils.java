package com.dangde.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebUtils {

  public static void copyBean(Object src, Object dest) {

    ConvertUtils.register(
        new Converter() {
          @Override
          public Object convert(Class type, Object value) {
            if (value == null) return null;
            String str = (String) value;
            if (str.trim().equals("")) return null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
              return df.parse(str.trim());
            } catch (ParseException e) {
              System.out.println("时间转换出现错误");
              throw new RuntimeException();
            }
          }
        },
        Date.class);
    try {
      BeanUtils.copyProperties(dest, src);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }
}
