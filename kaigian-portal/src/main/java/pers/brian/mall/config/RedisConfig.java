package pers.brian.mall.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import pers.brian.mall.common.config.BaseRedisConfig;

/**
 * @Description: Redis配置类
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
