package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.pms.dto.RelationAttrInfoDTO;
import pers.brian.mall.modules.pms.mapper.PmsProductAttributeMapper;
import pers.brian.mall.modules.pms.model.PmsProductAttribute;
import pers.brian.mall.modules.pms.service.PmsProductAttributeService;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {

    private final PmsProductAttributeMapper productAttributeMapper;

    @Autowired
    public PmsProductAttributeServiceImpl(PmsProductAttributeMapper productAttributeMapper) {
        this.productAttributeMapper = productAttributeMapper;
    }

    @Override
    public List<RelationAttrInfoDTO> getRelationAttrInfoByCid(Long cId) {
        return null;
    }

    @Override
    public Page<PmsProductAttribute> list(Long cId, Integer type, Integer pageNum, Integer pageSize) {
        Page<PmsProductAttribute> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductAttribute> productAttributeQueryWrapper = new QueryWrapper<>();
        productAttributeQueryWrapper.lambda()
                .eq(PmsProductAttribute::getProductAttributeCategoryId, cId)
                .eq(PmsProductAttribute::getType, type);
        return this.page(page, productAttributeQueryWrapper);
    }
}
