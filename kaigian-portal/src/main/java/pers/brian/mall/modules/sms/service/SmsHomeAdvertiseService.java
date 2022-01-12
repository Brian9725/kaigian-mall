package pers.brian.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.sms.model.SmsHomeAdvertise;

import java.util.List;

/**
 * 首页轮播广告表 服务类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {

    /**
     * 获取广告列表
     *
     * @return 广告列表
     */
    List<SmsHomeAdvertise> getHomeBanners();
}
