package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.oms.model.OmsOrder;
import pers.brian.mall.modules.oms.model.OmsOrderItem;

import java.util.List;

/**
 * @Description: 我的订单列表数据传输对象
 * @Author: BrianHu
 * @Create: 2021-12-08 12:01
 * @Version: 0.0.1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "我的订单列表数据传输对象", description = "我的订单列表数据传输对象")
public class OrderListDTO extends OmsOrder {
    private List<OmsOrderItem> orderItemList;
}
