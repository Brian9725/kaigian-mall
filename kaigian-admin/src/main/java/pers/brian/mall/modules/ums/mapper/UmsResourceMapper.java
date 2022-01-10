package pers.brian.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.brian.mall.dto.ResourceRoleDTO;
import pers.brian.mall.modules.ums.model.entity.UmsResource;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@Component
public interface UmsResourceMapper extends BaseMapper<UmsResource> {

    /**
     * 获取用户所有可访问资源
     *
     * @param adminId 用户id
     * @return 可访问资源列表
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 根据角色ID获取资源
     *
     * @param roleId 角色ID
     * @return 资源列表
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);

    /**
     * 获取角色资源映射关系
     *
     * @return 资源角色映射关系
     */
    List<ResourceRoleDTO> getAllResourceRole();
}
