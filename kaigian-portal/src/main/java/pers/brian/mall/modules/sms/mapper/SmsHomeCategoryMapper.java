package pers.brian.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import pers.brian.mall.dto.HomeGoodsSaleDTO;
import pers.brian.mall.modules.sms.model.SmsHomeCategory;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Component
public interface SmsHomeCategoryMapper extends BaseMapper<SmsHomeCategory> {

    /**
     * 获取销售商品列表
     *
     * @return 商品列表
     */
    List<HomeGoodsSaleDTO> getHomeCategoryWithProduct();
}
