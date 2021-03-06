package pers.brian.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import pers.brian.mall.dto.ProductDetailDTO;
import pers.brian.mall.modules.pms.model.PmsProduct;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-30
 */
@Component
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    /**
     * 根据商品id获取商品详情
     *
     * @param id 商品id
     * @return 商品详情
     */
    ProductDetailDTO getProductDetail(Long id);
}
