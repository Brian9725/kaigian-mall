package pers.brian.mall.modules.sms.service;

import pers.brian.mall.dto.HomeGoodsSaleDTO;
import pers.brian.mall.modules.sms.model.SmsHomeCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
public interface SmsHomeCategoryService extends IService<SmsHomeCategory> {

    /**
     * 获取销售商品列表
     *
     * @return 商品列表
     */
    List<HomeGoodsSaleDTO> getGoodsSale();
}
