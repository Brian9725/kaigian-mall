package pers.brian.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.brian.mall.modules.ums.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @Author: BrianHu
 * @Date: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 获取用户所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

}
