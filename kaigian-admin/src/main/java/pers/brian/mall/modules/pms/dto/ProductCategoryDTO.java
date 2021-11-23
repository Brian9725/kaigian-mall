package pers.brian.mall.modules.pms.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.pms.model.PmsProductCategory;

import java.util.List;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-22 13:46
 * @Version: 0.0.1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="商品分类的数据传输对象", description="用于商品分类添加、修改数据接收")
public class ProductCategoryDTO extends PmsProductCategory {
    private List<Long> productAttributeIdList;
}
