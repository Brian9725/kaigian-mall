package pers.brian.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.sms.mapper.SmsCouponHistoryMapper;
import pers.brian.mall.modules.sms.model.SmsCouponHistory;
import pers.brian.mall.modules.sms.service.SmsCouponHistoryService;

/**
 * 优惠券使用、领取历史表 服务实现类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements SmsCouponHistoryService {

}
