package pers.brian.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttribute;

import java.util.List;

/**
 * @author BrianHu
 * @create 2021-11-22 15:13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductAttributeCateDTO筛选属性数据传输对象", description = "用于商品分类--筛选属性下拉级联数据")
public class PmsProductAttributeCategoryDTO {
    /**
     * 商品类型id
     */
    private Long id;

    /**
     * 商品类型名称
     */
    private String name;

    /**
     * 商品属性二级级联
     */
    private List<PmsProductAttribute> productAttributeList;
}
