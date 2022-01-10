package pers.brian.mall.modules.oms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单列表删选条件
 *
 * @author BrianHu
 * @create 2021-12-07 14:07
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OmsOrderConditionDTO订单列表筛选条件", description = "用于订单列表筛选条件参数接收")
public class OmsOrderConditionDTO {

    private Integer pageNum;

    private Integer pageSize;
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    private String receiverKeyword;

    @ApiModelProperty(value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;

    @ApiModelProperty(value = "订单类型：0->正常订单；1->秒杀订单")
    private Integer orderType;

    @ApiModelProperty(value = "订单来源：0->PC订单；1->app订单")
    private Integer sourceType;

    @ApiModelProperty(value = "提交时间")
    private String createTime;
}
