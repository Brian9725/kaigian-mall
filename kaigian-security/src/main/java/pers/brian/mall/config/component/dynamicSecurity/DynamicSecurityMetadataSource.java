package pers.brian.mall.config.component.dynamicSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 动态权限数据源，用于获取动态权限规则
 * @Author: BrianHu
 * @Create: 2021-12-28 10:58
 * @Version: 0.0.1
 **/
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    // 所有的资源角色信息的map
    private static Map<RequestMatcher, List<ConfigAttribute>> configAttributeMap = null;

    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    /**
     * spring在创建bean的时候调用这个注解初始化方法
     * 读取到所有的资源角色信息
     */
    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    /**
     * 在资源分配的时候就清除掉
     */
    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 在清除后就会再次获取最新的资源角色信息
        if (configAttributeMap == null) {
            this.loadDataSource();
        }
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        // 获取当前访问的路径
        HttpServletRequest request = ((FilterInvocation) o).getRequest();
        // 循环所有资源角色信息
        for (RequestMatcher pattern : configAttributeMap.keySet()) {
            // 匹配上了
            if (pattern.matches(request)) {
                // 拿到角色信息
                configAttributes.addAll(configAttributeMap.get(pattern));
            }
        }
        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
