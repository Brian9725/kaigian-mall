package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import pers.brian.mall.modules.pms.model.dto.PmsProductAttributeCategoryDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttributeCategory;
import pers.brian.mall.modules.pms.service.PmsProductAttributeCategoryService;

import java.util.List;

/**
 * 产品属性分类表 服务实现类
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public Page<PmsProductAttributeCategory> page(Integer pageNum, Integer pageSize) {
        Page<PmsProductAttributeCategory> productAttributeCategoryPage = new Page<>(pageNum, pageSize);
        return this.page(productAttributeCategoryPage);
    }

    @Override
    public List<PmsProductAttributeCategoryDTO> getListWithAttr() {
        return productAttributeCategoryMapper.getListWithAttr();
    }

    @Override
    public boolean customSave(PmsProductAttributeCategory pmsProductAttributeCategory) {
        pmsProductAttributeCategory.setAttributeCount(0).setParamCount(0);
        return this.save(pmsProductAttributeCategory);
    }
}
