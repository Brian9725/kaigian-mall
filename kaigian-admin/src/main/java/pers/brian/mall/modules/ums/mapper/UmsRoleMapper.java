package pers.brian.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.brian.mall.modules.ums.model.entity.UmsRole;

import java.util.List;

/**
 * 后台用户角色表 Mapper 接口
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@Component
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 获取用户所有角色
     *
     * @param adminId 用户id
     * @return 拥有的角色列表
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

}
