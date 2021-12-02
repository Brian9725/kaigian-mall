package pers.brian.mall.modules.oms.service.impl;

import pers.brian.mall.modules.oms.model.entity.OmsCartItem;
import pers.brian.mall.modules.oms.mapper.OmsCartItemMapper;
import pers.brian.mall.modules.oms.service.OmsCartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements OmsCartItemService {

}
