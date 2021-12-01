package pers.brian.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import pers.brian.mall.modules.ums.model.entity.UmsAdminLoginLog;

/**
 * @Description: <p>
 * 后台用户登录日志表 Mapper 接口
 * </p>
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Component
public interface UmsAdminLoginLogMapper extends BaseMapper<UmsAdminLoginLog> {

}
