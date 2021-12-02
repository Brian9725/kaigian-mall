package pers.brian.mall.modules.sms.mapper;

import org.springframework.stereotype.Component;
import pers.brian.mall.dto.HomeGoodsSaleDTO;
import pers.brian.mall.modules.sms.model.SmsHomeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
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
