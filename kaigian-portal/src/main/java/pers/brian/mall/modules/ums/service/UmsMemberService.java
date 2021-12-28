package pers.brian.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import pers.brian.mall.modules.ums.model.UmsMember;

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

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UmsMember getMemberByUsername(String username);

    /**
     * 通过用户名获取用户信息（spring-security用）
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserDetails loadUserByUsername(String username);
}
