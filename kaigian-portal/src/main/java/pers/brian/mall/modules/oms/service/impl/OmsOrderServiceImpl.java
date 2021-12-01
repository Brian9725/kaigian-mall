package pers.brian.mall.modules.oms.service.impl;

import pers.brian.mall.modules.oms.model.OmsOrder;
import pers.brian.mall.modules.oms.mapper.OmsOrderMapper;
import pers.brian.mall.modules.oms.service.OmsOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {

}
