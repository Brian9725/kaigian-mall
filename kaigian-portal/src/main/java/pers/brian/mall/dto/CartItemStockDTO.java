package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.oms.model.OmsCartItem;

/**
 * @author BrianHu
 * @create 2021-12-03 15:42
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "购物车和库存的数据传输对象", description = "购物车和库存的数据传输对象")
public class CartItemStockDTO extends OmsCartItem {

    @ApiModelProperty("真实库存（实际库存-锁定库存）")
    private Integer stock;
}