package pers.brian.mall.modules.ums.model.dto;

import pers.brian.mall.modules.ums.model.entity.UmsMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台菜单节点封装
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    @ApiModelProperty(value = "子级菜单")
    private List<UmsMenuNode> children;
}
