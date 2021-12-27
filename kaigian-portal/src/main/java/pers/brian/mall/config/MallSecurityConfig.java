package pers.brian.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import pers.brian.mall.modules.ums.service.UmsMemberService;

/**
 * @Description: 前台security配置
 * @Author: BrianHu
 * @Create: 2021-12-27 10:25
 * @Version: 0.0.1
 **/
@Configuration
@EnableWebSecurity
public class MallSecurityConfig extends SecurityConfig {

    @Autowired
    private UmsMemberService memberService;

    /**
     * 认证交给spring-security
     *
     * @return 用户信息
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> memberService.loadUserByUsername(username);
    }
}
