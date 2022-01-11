package pers.brian.mall.modules.sms.service.impl;

import pers.brian.mall.modules.sms.model.entity.SmsCouponHistory;
import pers.brian.mall.modules.sms.mapper.SmsCouponHistoryMapper;
import pers.brian.mall.modules.sms.service.SmsCouponHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements SmsCouponHistoryService {

}
