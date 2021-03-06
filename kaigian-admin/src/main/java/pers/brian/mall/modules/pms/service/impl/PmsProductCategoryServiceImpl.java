package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.brian.mall.modules.pms.mapper.PmsProductCategoryMapper;
import pers.brian.mall.modules.pms.model.dto.PmsProductCategoryChildrenDTO;
import pers.brian.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductCategory;
import pers.brian.mall.modules.pms.model.entity.PmsProductCategoryAttributeRelation;
import pers.brian.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import pers.brian.mall.modules.pms.service.PmsProductCategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Autowired
    private PmsProductCategoryAttributeRelationService productCategoryAttributeRelationService;

    @Override
    public Page<PmsProductCategory> page(Long parentId, Integer pageNum, Integer pageSize) {
        Page<PmsProductCategory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(PmsProductCategory::getParentId, parentId)
                .orderByAsc(PmsProductCategory::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateNavStatus(Integer navStatus, List<Long> ids) {
        UpdateWrapper<PmsProductCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsProductCategory::getNavStatus, navStatus)
                .in(PmsProductCategory::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateShowStatus(Integer showStatus, List<Long> ids) {
        UpdateWrapper<PmsProductCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsProductCategory::getShowStatus, showStatus)
                .in(PmsProductCategory::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    public List<PmsProductCategoryChildrenDTO> listWithChildren() {
        return productCategoryMapper.listWithChildren();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean customSave(PmsProductCategoryDTO productCategoryDTO) {
        // 保存商品分类
        PmsProductCategory productCategory = new PmsProductCategory();
        // 通过BeanUtils 将productCategoryDTO的数据拷贝到productCategory
        // 为什么要拷贝：因为一定要通过this.save 去保存PmsProductCategory，因为只有它才映射了@TableName
        BeanUtils.copyProperties(productCategoryDTO, productCategory);
        // 由于商品数量 和级别 在表单中没有维护， 需要设置默认值
        productCategory.setProductCount(0);
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            // 如果有多级分类，根据parentId查出商品分类获取level+1
            // 由于只有2级分类，直接设置为1
            productCategory.setLevel(1);
        }
        this.save(productCategory);
        saveAttrRelation(productCategoryDTO, productCategory);
        return true;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean update(PmsProductCategoryDTO productCategoryDTO) {
        // 保存商品分类
        PmsProductCategory productCategory = new PmsProductCategory();
        // 通过BeanUtils 将productCategoryDTO的数据拷贝到productCategory
        // 为什么要拷贝：因为一定要通过this.save去保存PmsProductCategory，因为只有它才映射了@TableName
        BeanUtils.copyProperties(productCategoryDTO, productCategory);
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            // 如果有多级分类，根据parentId查出商品分类获取level+1
            // 由于只有2级分类，直接设置为1
            productCategory.setLevel(1);
        }
        this.updateById(productCategory);

        // 删除已保存的关联属性—根据商品分类id删除
        QueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId, productCategory.getId());
        // 未选择筛选属性时，下面的删除和保存操作均会返回false
        productCategoryAttributeRelationService.remove(queryWrapper);
        saveAttrRelation(productCategoryDTO, productCategory);
        return true;
    }

    /**
     * 添加关联属性
     *
     * @param productCategoryDTO controller接收到的参数
     * @param productCategory    包含了商品分类的id
     * @return 是否保存成功
     */
    private boolean saveAttrRelation(PmsProductCategoryDTO productCategoryDTO, PmsProductCategory productCategory) {
        List<Long> productAttributeIdList = productCategoryDTO.getProductAttributeIdList();
        List<PmsProductCategoryAttributeRelation> list = new ArrayList<>();
        for (Long attrId : productAttributeIdList) {
            // 得到分类保存后的主键id，保存商品分类筛选属性关系
            PmsProductCategoryAttributeRelation productCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
            productCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
            productCategoryAttributeRelation.setProductAttributeId(attrId);
            list.add(productCategoryAttributeRelation);
        }
        return productCategoryAttributeRelationService.saveBatch(list);
    }
}
