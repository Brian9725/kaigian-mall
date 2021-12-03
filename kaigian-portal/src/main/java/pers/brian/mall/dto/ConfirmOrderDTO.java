package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.oms.model.OmsCartItem;
import pers.brian.mall.modules.ums.model.UmsMemberReceiveAddress;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-12-03 16:46
 * @Version: 0.0.1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "确认订单页数据传输对象", description = "确认订单页数据传输对象")
public class ConfirmOrderDTO {

    @ApiModelProperty(value = "商品列表")
    private List<OmsCartItem> cartList;

    @ApiModelProperty(value = "所有商品的件数")
    private Integer productTotal;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal priceTotal;

    @ApiModelProperty(value = "运费")
    private BigDecimal freightAmount;

    @ApiModelProperty(value = "应付总额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "收货地址")
    private List<UmsMemberReceiveAddress> addressList;
}
