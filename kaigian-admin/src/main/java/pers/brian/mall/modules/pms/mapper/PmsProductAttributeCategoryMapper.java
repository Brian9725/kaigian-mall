package pers.brian.mall.modules.pms.mapper;

import org.springframework.stereotype.Component;
import pers.brian.mall.modules.pms.model.dto.PmsProductAttributeCategoryDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 产品属性分类表 Mapper 接口
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
@Component
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {

    /**
     * 筛选属性下拉级联数据
     *
     * @return 筛选属性下拉级联数据
     */
    List<PmsProductAttributeCategoryDTO> getListWithAttr();
}
