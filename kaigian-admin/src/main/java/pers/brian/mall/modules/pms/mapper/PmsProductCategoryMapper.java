package pers.brian.mall.modules.pms.mapper;

import org.springframework.stereotype.Component;
import pers.brian.mall.modules.pms.model.dto.PmsProductCategoryChildrenDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Component
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    /**
     * 查询商品分类及其子分类的列表
     * @return 商品分类及其子分类的列表
     */
    List<PmsProductCategoryChildrenDTO> listWithChildren();
}
