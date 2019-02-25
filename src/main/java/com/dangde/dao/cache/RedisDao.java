package com.dangde.dao.cache;

import com.dangde.domain.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisDao {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @SuppressWarnings("rawtypes")
  private RedisTemplate redisTemplate;

  @SuppressWarnings("rawtypes")
  public RedisDao(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @SuppressWarnings("unchecked")
  public void putMaterial(Material material) {
    try {
      redisTemplate
          .opsForValue()
          .set("Material" + material.getId(), material, 60 * 60, TimeUnit.SECONDS); // 缓存1个小时
    } catch (Exception e) {
      logger.error("putMaterial redis error :" + e.getMessage(), e);
      throw new RuntimeException("putMaterial redis error :" + e.getMessage());
    }
  }

  public Material getMaterial(long materialId) {
    Material material = (Material) redisTemplate.opsForValue().get("Material" + materialId);
    return material;
  }
}
