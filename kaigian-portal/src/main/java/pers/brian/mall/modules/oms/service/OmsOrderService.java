package pers.brian.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.dto.ConfirmOrderDTO;
import pers.brian.mall.dto.OrderDetailDTO;
import pers.brian.mall.dto.OrderParamDTO;
import pers.brian.mall.modules.oms.model.OmsOrder;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
public interface OmsOrderService extends IService<OmsOrder> {

    /**
     * 生成确认订单
     *
     * @param ids 商品id
     * @return 确认订单内容
     */
    ConfirmOrderDTO generateConfirmOrder(List<Long> ids);

    /**
     * 生成订单
     *
     * @param paramDTO 带生成订单的货品信息
     * @return 生成的订单信息
     */
    OmsOrder generateOrder(OrderParamDTO paramDTO);

    /**
     * 根据id获取订单详情
     *
     * @param id 订单id
     * @return 订单详情
     */
    OrderDetailDTO getOrderDetail(Long id);

    /**
     * 取消超时的订单
     */
    void cancelOverTimeOrder();
}
