package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.pms.model.PmsProduct;
import pers.brian.mall.modules.pms.model.PmsSkuStock;

import java.util.List;

/**
 * @author BrianHu
 * @create 2021-12-02 13:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductConditionDTO商品保存数据传输对象", description = "用于商品的添加、修改保存的参数接收")
public class ProductDetailDTO extends PmsProduct {

    /**
     * 商品属性相关
     */
    @ApiModelProperty(value = "商品属性相关")
    private List<PmsProductAttributeValueDTO> productAttributeValueList;

    /**
     * 商品sku库存信息
     */
    @ApiModelProperty(value = "商品sku库存信息")
    private List<PmsSkuStock> skuStockList;
}
