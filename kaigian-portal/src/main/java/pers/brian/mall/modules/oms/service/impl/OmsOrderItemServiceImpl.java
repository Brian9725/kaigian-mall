package pers.brian.mall.modules.oms.service.impl;

import pers.brian.mall.modules.oms.model.OmsOrderItem;
import pers.brian.mall.modules.oms.mapper.OmsOrderItemMapper;
import pers.brian.mall.modules.oms.service.OmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

}
