package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pers.brian.mall.modules.pms.model.dto.PmsRelationAttrInfoDTO;
import pers.brian.mall.modules.pms.mapper.PmsProductAttributeMapper;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttribute;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttributeCategory;
import pers.brian.mall.modules.pms.service.PmsProductAttributeCategoryService;
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

    private final PmsProductAttributeCategoryService productAttributeCategoryService;

    @Autowired
    public PmsProductAttributeServiceImpl(PmsProductAttributeMapper productAttributeMapper, PmsProductAttributeCategoryService productAttributeCategoryService) {
        this.productAttributeMapper = productAttributeMapper;
        this.productAttributeCategoryService = productAttributeCategoryService;
    }

    @Override
    public List<PmsRelationAttrInfoDTO> getRelationAttrInfoByCid(Long cId) {
        return null;
    }

    @Override
    public Page<PmsProductAttribute> list(Long cId, Integer type, Integer pageNum, Integer pageSize) {
        Page<PmsProductAttribute> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(PmsProductAttribute::getProductAttributeCategoryId, cId)
                .eq(PmsProductAttribute::getType, type);
        return this.page(page, queryWrapper);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean create(PmsProductAttribute productAttribute) {
        // 1.添加新增的商品类型属性
        boolean saved = this.save(productAttribute);
        if (saved) {
            UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
            // 分开处理属性和参数
            if (productAttribute.getType() == 0) {
                updateWrapper.setSql("attribute_count = attribute_count + 1");
            } else if (productAttribute.getType() == 1) {
                updateWrapper.setSql("param_count = param_count + 1");
            }
            updateWrapper.lambda().eq(PmsProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
            productAttributeCategoryService.update(updateWrapper);
        }
        return saved;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            // TODO:处理ids中的id来自于多个商品类型时的情况
            // 查询相关商品类型信息
            PmsProductAttribute productAttribute = this.getById(ids.get(0));
            // 删除商品类型属性信息
            int deleteCounts = productAttributeMapper.deleteBatchIds(ids);
            if (productAttribute != null) {
                UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
                // 分开处理属性和参数
                if (productAttribute.getType() == 0) {
                    updateWrapper.setSql("attribute_count = attribute_count - " + deleteCounts);
                } else if (productAttribute.getType() == 1) {
                    updateWrapper.setSql("param_count = param_count - " + deleteCounts);
                }
                updateWrapper.lambda().eq(PmsProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
                productAttributeCategoryService.update(updateWrapper);
            }
            return (deleteCounts > 0);
        }
        return false;
    }
}
