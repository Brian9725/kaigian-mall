package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.dto.RelationAttrInfoDTO;
import pers.brian.mall.modules.pms.model.PmsProductAttribute;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {

    /**
     * 根据商品分类id获取关联的筛选属性
     *
     * @param cId 商品分类id
     * @return 关联的筛选属性
     */
    List<RelationAttrInfoDTO> getRelationAttrInfoByCid(Long cId);
}
