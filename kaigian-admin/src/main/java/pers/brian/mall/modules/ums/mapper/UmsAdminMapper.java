package pers.brian.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.brian.mall.modules.ums.model.entity.UmsAdmin;

import java.util.List;

/**
 * @Description: <p>
 * 后台用户表 Mapper 接口
 * </p>
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Component
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    /**
     * 获取资源相关用户ID列表
     *
     * @param resourceId 资源id
     * @return 相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);

}
