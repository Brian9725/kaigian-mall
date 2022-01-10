package pers.brian.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BrianHu
 * @create 2021-11-29 15:30
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductUpdateInitDTO修改的商品初始化数据", description = "用于商品修改数据初始化")
public class ProductUpdateInitDTO extends ProductSaveParamsDTO {

    private static final long serialVersionUID = -1978301466883717906L;

    /**
     * 一级分类id
     */
    private Long cateParentId;
}