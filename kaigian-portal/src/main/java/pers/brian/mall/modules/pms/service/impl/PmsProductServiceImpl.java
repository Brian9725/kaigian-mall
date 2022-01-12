package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.dto.ProductDetailDTO;
import pers.brian.mall.modules.pms.mapper.PmsProductMapper;
import pers.brian.mall.modules.pms.model.PmsProduct;
import pers.brian.mall.modules.pms.service.PmsProductService;

/**
 * 商品信息 服务实现类
 *
 * @author BrianHu
 * @create 2021-11-30 12:00
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public ProductDetailDTO getProductDetail(Long id) {
        return productMapper.getProductDetail(id);
    }
}
