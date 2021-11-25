package pers.brian.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import pers.brian.mall.modules.pms.model.po.PmsProductCategory;

import java.util.List;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-19 16:15
 * @Version: 0.0.1
 **/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductCategoryChildrenDTO商品一级分类和二级分类的级联传输对象", description = "用于商品列表--商品分类下拉级联数据")
public class PmsProductCategoryChildrenDTO {

    /**
     * 商品分类id
     */
    private Long id;

    /**
     * 商品分类名称
     */
    private String name;

    /**
     * 商品分类二级级联
     */
    private List<PmsProductCategory> children;
}
