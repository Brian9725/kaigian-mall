package pers.brian.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.dto.ConfirmOrderDTO;
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
}
