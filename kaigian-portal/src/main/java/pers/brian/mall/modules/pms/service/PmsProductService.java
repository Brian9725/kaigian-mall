package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.dto.ProductDetailDTO;
import pers.brian.mall.modules.pms.model.PmsProduct;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-30
 */
public interface PmsProductService extends IService<PmsProduct> {

    /**
     * 根据商品id获取商品详情
     *
     * @param id 商品id
     * @return 商品详情
     */
    ProductDetailDTO getProductDetail(Long id);
}
