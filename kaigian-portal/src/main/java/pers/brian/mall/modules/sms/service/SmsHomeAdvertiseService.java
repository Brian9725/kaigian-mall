package pers.brian.mall.modules.sms.service;

import pers.brian.mall.modules.sms.model.SmsHomeAdvertise;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {

    /**
     * 获取广告列表
     *
     * @return 广告列表
     */
    List<SmsHomeAdvertise> getHomeBanners();
}
