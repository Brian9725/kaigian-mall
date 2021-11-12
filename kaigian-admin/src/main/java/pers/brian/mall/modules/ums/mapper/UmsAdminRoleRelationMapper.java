package pers.brian.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import pers.brian.mall.modules.ums.model.UmsAdminRoleRelation;

import javax.annotation.Resource;

/**
 * @Description:
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Component
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {

}
