package com.dangde.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configdangde {

  public static String UPLOAD_PATH_IMAGE = null;
  public static String UPLOAD_PATH_DOCUMENT = null;
  private static Logger logger = LoggerFactory.getLogger(Configdangde.class);

  static {

    //		1、基于ClassLoder读取配置文件
    //		该方式只能读取类路径下的配置文件，有局限但是如果配置文件在类路径下比较方便。
    Properties properties = new Properties();

    // 使用ClassLoader加载properties配置文件生成对应的输入流
    InputStream in = Configdangde.class.getClassLoader().getResourceAsStream("config.properties");
    // 使用properties对象加载输入流
    try {
      properties.load(in);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      logger.error("加载配置文件出错   inner error :" + e.getMessage(), e);
    }
    // 获取key对应的value值
    UPLOAD_PATH_IMAGE = properties.getProperty("uploadpath_images");
    UPLOAD_PATH_DOCUMENT = properties.getProperty("uploadpath_documents");
  }
}
