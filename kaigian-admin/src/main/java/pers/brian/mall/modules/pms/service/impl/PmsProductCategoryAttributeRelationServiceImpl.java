package pers.brian.mall.modules.pms.service.impl;

import pers.brian.mall.modules.pms.model.entity.PmsProductCategoryAttributeRelation;
import pers.brian.mall.modules.pms.mapper.PmsProductCategoryAttributeRelationMapper;
import pers.brian.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements PmsProductCategoryAttributeRelationService {

}
