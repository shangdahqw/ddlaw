package com.dangde.dao.cache;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.dangde.domain.Material;
import com.dangde.utils.TokenModel;

/**
 * Created by codingBoy on 17/2/17.
 */
public class RedisDao {
	
	
    private Logger logger= LoggerFactory.getLogger(this.getClass());

	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	@SuppressWarnings("rawtypes")
	public RedisDao(RedisTemplate redisTemplate){
		this.redisTemplate = redisTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public void putMaterial(Material material) {
		try {
			redisTemplate.opsForValue().set("Material"+material.getId(),material,60*60,TimeUnit.SECONDS);//缓存1个小时
		}catch(Exception e){
			logger.error("putMaterial redis error :"+e.getMessage(),e);
			throw new RuntimeException("putMaterial redis error :"+e.getMessage());
		}
		
	}
	
	public Material getMaterial(long material_id) {
		Material material = (Material)redisTemplate.opsForValue().get("Material"+material_id);
		return material;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void putTokenModel(TokenModel tokenModel) {
		try {
			redisTemplate.opsForValue().set("tokenModel"+tokenModel.getUserId(),tokenModel,60*60,TimeUnit.SECONDS);//缓存1个小时
		}catch(Exception e){
			logger.error("putTokenModel redis error :"+e.getMessage(),e);
			throw new RuntimeException("putTokenModel redis error :"+e.getMessage());
		}

	}
		
	
	public TokenModel getTokenModel(long user_id) {
	
		TokenModel tokenModel = (TokenModel)redisTemplate.opsForValue().get("tokenModel"+user_id);
		return tokenModel;
	}
	
	@SuppressWarnings("unchecked")
	public void logout(long user_id) {
		
		try {
			redisTemplate.delete("tokenModel"+user_id);
		} catch (Exception e) {
			logger.error("logout redis error :"+e.getMessage(),e);
			throw new RuntimeException("logout redis error :"+e.getMessage());
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//    private final JedisPool jedisPool;

//    public RedisDao(String ip, int port) {
//        jedisPool = new JedisPool(ip, port);
//    }
    
//    private RuntimeSchema<Material> schema = RuntimeSchema.createFrom(Material.class);
//
//    public Material getMaterial(long MaterialId) {
//        //redis操作逻辑
//        try {
//            Jedis jedis = jedisPool.getResource();
//            try {
//                String key = "Material:" + MaterialId;
//                //并没有实现哪部序列化操作
//                //采用自定义序列化
//                //protostuff: pojo.
//                byte[] bytes = jedis.get(key.getBytes());
//                //缓存重获取到
//                if (bytes != null) {
//                    Material Material=schema.newMessage();
//                    ProtostuffIOUtil.mergeFrom(bytes,Material,schema);
//                    //Material被反序列化
//
//                    return Material;
//                }
//            }finally {
//                jedis.close();
//            }
//        }catch (Exception e) {
//
//        }
//        return null;
//    }
//
//    public String putMaterial(Material Material) {
//        try {
//            Jedis jedis = jedisPool.getResource();
//            try {
//                String key = "Material:" + Material.getId();
//                byte[] bytes = ProtostuffIOUtil.toByteArray(Material, schema,
//                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
//                //超时缓存
//                int timeout = 60 * 60;//1小时
//                String result = jedis.setex(key.getBytes(),timeout,bytes);
//
//                return result;
//            }finally {
//                jedis.close();
//            }
//        }catch (Exception e) {
//
//        }
//
//        return null;
//    }
    
    
    
    
}
