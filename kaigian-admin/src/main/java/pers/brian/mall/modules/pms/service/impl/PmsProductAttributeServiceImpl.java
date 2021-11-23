package pers.brian.mall.modules.pms.service.impl;

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
}
