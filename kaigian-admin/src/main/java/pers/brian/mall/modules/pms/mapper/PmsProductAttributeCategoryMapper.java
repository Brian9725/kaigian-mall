package pers.brian.mall.modules.pms.mapper;

import org.springframework.stereotype.Component;
import pers.brian.mall.modules.pms.dto.ProductAttributeCateDTO;
import pers.brian.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Component
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {

    List<ProductAttributeCateDTO> getListWithAttr();
}
