package pers.brian.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.brian.mall.modules.ums.model.entity.UmsMenu;

import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@Component
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

    /**
     * 根据后台用户ID获取菜单
     *
     * @param adminId 用户ID
     * @return 菜单列表
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    /**
     * 根据角色ID获取菜单
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

}
