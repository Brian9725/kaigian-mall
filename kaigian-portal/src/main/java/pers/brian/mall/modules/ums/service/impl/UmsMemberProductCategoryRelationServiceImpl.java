package pers.brian.mall.modules.ums.service.impl;

import pers.brian.mall.modules.ums.model.UmsMemberProductCategoryRelation;
import pers.brian.mall.modules.ums.mapper.UmsMemberProductCategoryRelationMapper;
import pers.brian.mall.modules.ums.service.UmsMemberProductCategoryRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class UmsMemberProductCategoryRelationServiceImpl extends ServiceImpl<UmsMemberProductCategoryRelationMapper, UmsMemberProductCategoryRelation> implements UmsMemberProductCategoryRelationService {

}
