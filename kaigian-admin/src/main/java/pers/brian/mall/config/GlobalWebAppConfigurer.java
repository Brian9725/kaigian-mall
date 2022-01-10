package pers.brian.mall.config;

import pers.brian.mall.interceptor.AuthInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 权限验证拦截器
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@Configuration
public class GlobalWebAppConfigurer implements WebMvcConfigurer {

    /**
     * 该拦截器主要是为了权限验证
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
    }

    @Bean
    @ConfigurationProperties(prefix = "secure.ignored")
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
