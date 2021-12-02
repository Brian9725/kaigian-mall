package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-12-02 17:30
 * @Version: 0.0.1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "添加购物车参数接收对象", description = "添加购物车参数接收对象")
public class AddCartDTO {

    private Long productId;

    private Long productSkuId;

    private Integer quantity;
}
