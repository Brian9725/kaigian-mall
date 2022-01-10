package pers.brian.mall.dto;

import lombok.Data;
import pers.brian.mall.modules.ums.model.entity.UmsResource;
import pers.brian.mall.modules.ums.model.entity.UmsRole;

import java.util.List;

/**
 * 资源-角色DTO
 *
 * @author BrianHu
 * @create 2021-12-27 17:33
 **/
@Data
public class ResourceRoleDTO extends UmsResource {

    private List<UmsRole> roleList;
}
