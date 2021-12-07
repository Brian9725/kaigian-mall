package pers.brian.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.brian.mall.dto.OrderDetailDTO;
import pers.brian.mall.modules.oms.model.OmsOrder;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    /**
     * 根据id获取订单详情
     *
     * @param id 订单id
     * @return 订单详情
     */
    OrderDetailDTO getOrderDetail(Long id);
}
