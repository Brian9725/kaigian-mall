package pers.brian.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.oms.mapper.OmsOrderItemMapper;
import pers.brian.mall.modules.oms.model.entity.OmsOrderItem;
import pers.brian.mall.modules.oms.service.OmsOrderItemService;

/**
 * 订单中所包含的商品 服务实现类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

}
