package com.refactor.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor());
        //interceptorRegistration.excludePathPatterns(new String[]{"/userlogin", "/", "/index","*.js","*.css","/debug"});
        //interceptorRegistration.addPathPatterns("/**");

        //InterceptorRegistration interceptor = registry.addInterceptor(new DebugInterceptorHandler());
        //interceptor.addPathPatterns("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("dashboard");
        registry.addViewController("/security/admin").setViewName("security/admin");
        registry.addViewController("/security/user").setViewName("security/user");
        registry.addViewController("/userlogin").setViewName("dashboard");
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}
