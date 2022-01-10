package pers.brian.mall.config;

import pers.brian.mall.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
