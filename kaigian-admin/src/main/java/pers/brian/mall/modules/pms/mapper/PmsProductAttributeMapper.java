package pers.brian.mall.modules.pms.mapper;

import org.springframework.stereotype.Component;
import pers.brian.mall.modules.pms.model.dto.PmsRelationAttrInfoDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Component
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {

    /**
     * 根据商品分类id获取关联的筛选属性
     *
     * @param cId 商品分类id
     * @return 关联的筛选属性
     */
    List<PmsRelationAttrInfoDTO> getRelationAttrInfoByCid(Long cId);
}
