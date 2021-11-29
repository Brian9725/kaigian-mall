package pers.brian.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.model.dto.PmsProductConditionDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProduct;
import pers.brian.mall.modules.pms.service.PmsProductService;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {

    private final PmsProductService productService;

    @Autowired
    public PmsProductController(PmsProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> list(PmsProductConditionDTO productConditionDTO) {
        Page<PmsProduct> productPage = productService.list(productConditionDTO);
        return CommonResult.success(CommonPage.restPage(productPage));
    }

    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateDeleteStatus(@RequestParam(value = "deleteStatus", defaultValue = "0") Integer deleteStatus,
                                                    @RequestParam("ids") List<Long> ids) {
        boolean removed = productService.removeByIds(ids);
        if (removed) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }
}

