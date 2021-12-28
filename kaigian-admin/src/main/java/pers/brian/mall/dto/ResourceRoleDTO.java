package pers.brian.mall.dto;

import lombok.Data;
import pers.brian.mall.modules.ums.model.entity.UmsResource;
import pers.brian.mall.modules.ums.model.entity.UmsRole;

import java.util.List;

/**
 * @Description: 资源-角色DTO
 * @Author: BrianHu
 * @Create: 2021-12-27 17:33
 * @Version: 0.0.1
 **/
@Data
public class ResourceRoleDTO extends UmsResource {

    private List<UmsRole> roleList;
}
