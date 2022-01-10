package pers.brian.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.brian.mall.modules.sms.model.SmsHomeAdvertise;
import pers.brian.mall.modules.sms.mapper.SmsHomeAdvertiseMapper;
import pers.brian.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页轮播广告表 服务实现类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Override
    public List<SmsHomeAdvertise> getHomeBanners() {
        QueryWrapper<SmsHomeAdvertise> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SmsHomeAdvertise::getType, 0)
                .eq(SmsHomeAdvertise::getStatus, 1)
                .orderByAsc(SmsHomeAdvertise::getSort);
        return this.list(queryWrapper);
    }
}
