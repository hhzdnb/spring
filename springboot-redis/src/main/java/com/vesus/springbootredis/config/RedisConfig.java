package com.vesus.springbootredis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.persistence.Cacheable;
import java.lang.reflect.Method;


//Redis 缓存配置类
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    //缓存对象集合中，缓存是以key-value的形式保存的,当不指定缓存key是，会使用KeyGenerator生成key
    //定义缓存数据 key 生成策略的bean包名+类名+方法名+所有参数
    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(target.getClass().getName());
                buffer.append(method.getName());
                for (Object obj : params) {
                    buffer.append(obj.toString());
                }
                return buffer.toString();
            }
        };
    };
    //要启用spring缓存支持,需创建一个 CacheManager的 bean，CacheManager 接口有很多实现，
    // 这里Redis 的集成，用 RedisCacheManager这个实现类
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager ;
    }
    //redis模板，Spring Data Redis提供了两个模板：RedisTemplate,StringRedisTemplate
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);
        //序列化
        //spring data redis提供多个序列化器
        //GenericToStringSerializer：使用Spring转换服务进行序列化；
        //JacksonJsonRedisSerializer：使用Jackson 1，将对象序列化为JSON；
        //Jackson2JsonRedisSerializer：使用Jackson 2，将对象序列化为JSON；
        //JdkSerializationRedisSerializer：使用Java序列化；
        //OxmSerializer：使用Spring O/X映射的编排器和解排器（marshaler和unmarshaler）实
        //现序列化，用于XML序列化；
        //StringRedisSerializer：序列化String类型的key和value。
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(om);
        template.setValueSerializer(serializer);
        template.afterPropertiesSet();
        return template ;

    }
}
