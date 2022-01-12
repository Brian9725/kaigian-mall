package pers.brian.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import pers.brian.mall.config.component.SecurityResourceRoleSource;
import pers.brian.mall.config.component.dynamicSecurity.DynamicSecurityService;
import pers.brian.mall.dto.ResourceRoleDTO;
import pers.brian.mall.modules.ums.service.UmsAdminService;
import pers.brian.mall.modules.ums.service.UmsResourceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 后台security配置
 *
 * @author BrianHu
 * @create 2021-12-27 17:07
 **/
@Configuration
@EnableWebSecurity
public class MallSecurityConfig extends SecurityConfig {

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private UmsResourceService umsResourceService;

    /**
     * 认证交给spring-security
     *
     * @return 用户信息
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> umsAdminService.loadUserByUsername(username);
    }

    /**
     * 为spring-security配置的资源角色信息（静态）
     *
     * @return 资源-角色映射
     */
    // 取消静态资源授权
    // @Bean
    public SecurityResourceRoleSource securityResourceRoleSource() {
        return () -> {
            // 调用业务逻辑类查询资源对应角色信息
            List<ResourceRoleDTO> list = umsResourceService.getAllResourceRole();
            Map<String, List<String>> map = new HashMap<>(16);
            for (ResourceRoleDTO resourceRoleDTO : list) {
                List<String> roleNameList = resourceRoleDTO.getRoleList()
                        .stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList());

                map.put(resourceRoleDTO.getUrl(), roleNameList);
            }
            return map;
        };
    }

    // 获取最新的资源角色信息
    @Bean("dynamicSecurityService")
    public DynamicSecurityService dynamicSecurityService(UmsResourceService umsResourceService) {
        return () -> {
            Map<RequestMatcher, List<ConfigAttribute>> map = new ConcurrentHashMap<>();

            List<ResourceRoleDTO> list = umsResourceService.getAllResourceRole();
            for (ResourceRoleDTO resource : list) {
                // 通配符匹配器
                map.put(new AntPathRequestMatcher(resource.getUrl()),
                        // 所有角色信息
                        resource.getRoleList().stream()
                                .map(role -> new org.springframework.security.access.SecurityConfig(role.getName()))
                                .collect(Collectors.toList())
                );
            }
            return map;
        };
    }
}

