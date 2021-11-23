package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.pms.dto.ProductAttributeCateDTO;
import pers.brian.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import pers.brian.mall.modules.pms.model.PmsProductAttributeCategory;
import pers.brian.mall.modules.pms.service.PmsProductAttributeCategoryService;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    private final PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Autowired
    public PmsProductAttributeCategoryServiceImpl(PmsProductAttributeCategoryMapper productAttributeCategoryMapper) {
        this.productAttributeCategoryMapper = productAttributeCategoryMapper;
    }

    @Override
    public Page<PmsProductAttributeCategory> page(Integer pageNum, Integer pageSize) {
        Page<PmsProductAttributeCategory> productAttributeCategoryPage = new Page<>(pageNum, pageSize);
        return this.page(productAttributeCategoryPage);
    }

    @Override
    public List<ProductAttributeCateDTO> getListWithAttr() {
        return productAttributeCategoryMapper.getListWithAttr();
    }
}
