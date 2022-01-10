package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author BrianHu
 * @create 2021-11-30 16:19
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "首页类型导航栏数据", description = "首页类型导航栏数据")
public class HomeMenusDTO {
    private Long id;

    private String name;

    private List<ProductDTO> productList;
}
