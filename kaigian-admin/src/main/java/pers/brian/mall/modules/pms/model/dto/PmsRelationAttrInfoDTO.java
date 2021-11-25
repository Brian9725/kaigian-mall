package pers.brian.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-22 15:32
 * @Version: 0.0.1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductAttributeCateDTO分类和筛选属性管理数据", description = "用于筛选属性的已报错数据初始化")
public class PmsRelationAttrInfoDTO {
    /**
     * 商品类型id
     */
    private Long attributeCategoryId;

    /**
     * 商品属性Id
     */
    private Long attributeId;
}
