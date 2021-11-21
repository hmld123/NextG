package com.github.hmld.common.utils.redis;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
/**
 * 
 * @author hmld
 * Redis 序列化工具
 * @param <T>
 */
public class JsonRedisSerializer<T> implements RedisSerializer<T>{

  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

  private Class<T> clazz;

	@SuppressWarnings("unused")
	private ObjectMapper objectMapper = new ObjectMapper();

  static
  {
      ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
  }

  public JsonRedisSerializer(Class<T> clazz)
  {
      super();
      this.clazz = clazz;
  }

  @Override
  public byte[] serialize(T t) throws SerializationException
  {
      if (t == null)
      {
          return new byte[0];
      }
      return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
  }

  @Override
  public T deserialize(byte[] bytes) throws SerializationException
  {
      if (bytes == null || bytes.length <= 0)
      {
          return null;
      }
      String str = new String(bytes, DEFAULT_CHARSET);

      return JSON.parseObject(str, clazz);
  }

  public void setObjectMapper(ObjectMapper objectMapper)
  {
      Assert.notNull(objectMapper, "'objectMapper' must not be null");
      this.objectMapper  = objectMapper;
  }

  protected JavaType getJavaType(Class<?> clazz)
  {
      return TypeFactory.defaultInstance().constructType(clazz);
  }
}
