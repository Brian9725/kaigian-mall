package pers.brian.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.oms.model.dto.OmsOrderConditionDTO;
import pers.brian.mall.modules.oms.model.entity.OmsOrder;

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
     * 根据条件查询订单列表
     *
     * @param conditionDTO 查询条件
     * @return 订单列表
     */
    Page<OmsOrder> list(OmsOrderConditionDTO conditionDTO);
}
