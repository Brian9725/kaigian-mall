package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.pms.model.PmsProductAttributeValue;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-12-02 13:55
 * @Version: 0.0.1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SPU的数据传输对象", description = "主要用于商品详情展示")
public class PmsProductAttributeValueDTO extends PmsProductAttributeValue {

    private String attrName;
}
