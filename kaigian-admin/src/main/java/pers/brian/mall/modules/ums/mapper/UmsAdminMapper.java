package pers.brian.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.brian.mall.modules.ums.model.entity.UmsAdmin;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
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
