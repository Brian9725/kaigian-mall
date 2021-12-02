package pers.brian.mall.modules.ums.service;

import pers.brian.mall.modules.ums.model.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
public interface UmsMemberService extends IService<UmsMember> {

    /**
     * 前台用户注册
     *
     * @param umsMemberParam 用户信息
     * @return 注册完成的用户信息
     */
    UmsMember register(UmsMember umsMemberParam);

    /**
     * 前台用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return 登陆的用户信息
     */
    UmsMember login(String username, String password);

    /**
     * 获取当前登陆的用户
     *
     * @return 当前登陆的用户
     */
    UmsMember getCurrentMember();
}
