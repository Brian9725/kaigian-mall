package pers.brian.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import pers.brian.mall.domain.AdminUserDetails;
import pers.brian.mall.modules.ums.model.dto.UmsAdminParam;
import pers.brian.mall.modules.ums.model.dto.UpdateAdminPasswordParam;
import pers.brian.mall.modules.ums.model.entity.UmsAdmin;
import pers.brian.mall.modules.ums.model.entity.UmsResource;
import pers.brian.mall.modules.ums.model.entity.UmsRole;

import java.util.List;

/**
 * @Description: 后台管理员管理Service
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
public interface UmsAdminService extends IService<UmsAdmin> {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    UmsAdmin login(String username, String password);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    boolean update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     */
    boolean delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional(rollbackFor = Exception.class)
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    AdminUserDetails loadUserByUsername(String username);
}
