package pers.brian.mall.config.component;

import java.util.List;
import java.util.Map;

/**
 * 资源-角色
 *
 * @author BrianHu
 * @create 2021-12-27 17:28
 **/
public interface SecurityResourceRoleSource {
    /**
     * 获取所有资源对应的角色
     * key: 资源： /product/**
     * value: 角色
     *
     * @return 资源-角色映射map
     */
    Map<String, List<String>> getResourceRole();
}
