package pers.brian.mall.modules.ums.service;

import pers.brian.mall.modules.ums.model.UmsMember;

/**
 * @Description: 前台用户缓存管理Service
 * @Author: BrianHu
 * @Create: 2021-12-03 12:07
 * @Version: 0.0.1
 **/
public interface UmsMemberCacheService {
    /**
     * 删除后台用户缓存
     *
     * @param adminId 用户id
     */
    void delMember(Long adminId);


    /**
     * 获取缓存后台用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UmsMember getUser(String username);

    /**
     * 设置缓存后台用户信息
     *
     * @param admin 用户信息
     */
    void setUser(UmsMember admin);
}
