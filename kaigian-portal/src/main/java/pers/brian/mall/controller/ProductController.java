package pers.brian.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.dto.ProductDetailDTO;
import pers.brian.mall.modules.pms.service.PmsProductService;

/**
 * @Description: 商品控制器
 * @Author: BrianHu
 * @Create: 2021-12-02 13:51
 * @Version: 0.0.1
 **/
@RestController
@Api(tags = "ProductController")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private PmsProductService productService;

    @RequestMapping("/detail/{id}")
    @ResponseBody
    public CommonResult<ProductDetailDTO> getProductDetail(@PathVariable Long id) {
        ProductDetailDTO productDetailDTO = productService.getProductDetail(id);
        return CommonResult.success(productDetailDTO);
    }
}
