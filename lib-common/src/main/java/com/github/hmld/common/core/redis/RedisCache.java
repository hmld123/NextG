package com.github.hmld.common.core.redis;
/**
 * redis 工具类
 * @author hmld
 *
 */

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
/**
 * redis 工具
 * @author hmld
 *
 */
@Component
public class RedisCache {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 缓存基本的对象
	 * @param key 缓存的键
	 * @param value 缓存的内容
	 */
	public void setCacheObject(final String key,final Object value) {
		redisTemplate.opsForValue().set(key, value);
	}
	/**
	 * 缓存基本的对象
	 * @param key 缓存的键
	 * @param value 缓存的内容
	 * @param timeout 时间
	 * @param timeUnit 时间粒度
	 */
	public void setCacheObject(final String key,final Object value,final Integer timeout, final TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}
	
	/**
	 * 设置有效时间
	 * @param key 缓存的键
	 * @param timeout 时间
	 * @return true=设置成功；false=设置失败
	 */
	public boolean expire(final String key,final Integer timeout) {
		return redisTemplate.expire(key, timeout,TimeUnit.SECONDS);
	}
	
	/**
	 * 设置有效时间
	 * @param key 缓存的键
	 * @param timeout 时间
	 * @param timeUnit 时间粒度
	 * @return true=设置成功；false=设置失败
	 */
	public boolean expire(final String key,final Integer timeout, final TimeUnit timeUnit) {
		return redisTemplate.expire(key, timeout,timeUnit);
	}
	
	/**
	 * 获取对象
	 * @param key 缓存的键
	 * @return 数据
	 */
	public Object getCacheObject(final String key){
		ValueOperations<String, Object> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}
	
	/**
	 * 删除单个对象
	 * @param key 缓存的键
	 * @return true=删除成功；false=删除失败
	 */
	public boolean deleteObject(final String key) {
		return redisTemplate.delete(key);
	}
	
}
