package pers.brian.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.ums.mapper.UmsMemberProductCategoryRelationMapper;
import pers.brian.mall.modules.ums.model.UmsMemberProductCategoryRelation;
import pers.brian.mall.modules.ums.service.UmsMemberProductCategoryRelationService;

/**
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Service
public class UmsMemberProductCategoryRelationServiceImpl extends ServiceImpl<UmsMemberProductCategoryRelationMapper, UmsMemberProductCategoryRelation> implements UmsMemberProductCategoryRelationService {

}
