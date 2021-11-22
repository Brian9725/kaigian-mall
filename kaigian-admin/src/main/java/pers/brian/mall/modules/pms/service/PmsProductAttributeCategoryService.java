package pers.brian.mall.modules.pms.service;

import pers.brian.mall.modules.pms.dto.ProductAttributeCateDTO;
import pers.brian.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    /**
     * 筛选属性下拉级联数据
     *
     * @return 筛选属性下拉级联数据
     */
    List<ProductAttributeCateDTO> getListWithAttr();
}
