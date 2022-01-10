package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.oms.model.OmsOrder;
import pers.brian.mall.modules.oms.model.OmsOrderItem;

import java.util.List;

/**
 * 我的订单列表数据传输对象
 *
 * @author BrianHu
 * @create 2021-12-08 12:01
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "我的订单列表数据传输对象", description = "我的订单列表数据传输对象")
public class OrderListDTO extends OmsOrder {
    private List<OmsOrderItem> orderItemList;
}
