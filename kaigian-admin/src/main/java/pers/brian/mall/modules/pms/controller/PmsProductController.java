package pers.brian.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.model.dto.PmsProductConditionDTO;
import pers.brian.mall.modules.pms.model.dto.ProductSaveParamsDTO;
import pers.brian.mall.modules.pms.model.dto.ProductUpdateInitDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProduct;
import pers.brian.mall.modules.pms.service.PmsProductService;

import javax.validation.Valid;
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
                                                    @RequestParam(value = "ids", defaultValue = "") List<Long> ids) {
        boolean removed = productService.removeByIds(ids);
        if (removed) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateNewStatus(@RequestParam(value = "newStatus", defaultValue = "0") Integer newStatus,
                                                 @RequestParam(value = "ids", defaultValue = "") List<Long> ids) {
        boolean updated = productService.updateStatus(newStatus, ids, PmsProduct::getNewStatus);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam(value = "recommendStatus", defaultValue = "0") Integer recommendStatus,
                                                       @RequestParam(value = "ids", defaultValue = "") List<Long> ids) {
        boolean updated = productService.updateStatus(recommendStatus, ids, PmsProduct::getRecommandStatus);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updatePublishStatus(@RequestParam(value = "publishStatus", defaultValue = "0") Integer publishStatus,
                                                     @RequestParam(value = "ids", defaultValue = "") List<Long> ids) {
        boolean updated = productService.updateStatus(publishStatus, ids, PmsProduct::getPublishStatus);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> create(@RequestBody ProductSaveParamsDTO productSaveParamsDTO) {
        boolean saved = productService.create(productSaveParamsDTO);
        if (saved) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/updateInfo/{id}")
    @ResponseBody
    public CommonResult<ProductUpdateInitDTO> getUpdateInfo(@PathVariable Long id) {
        // TODO:商品编辑页面的商品分类信息显示bug
        ProductUpdateInitDTO updateInitDTO = productService.getUpdateInfo(id);
        return CommonResult.success(updateInitDTO);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody @Valid ProductSaveParamsDTO productSaveParamsDTO) {
        boolean updated = productService.update(productSaveParamsDTO);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }
}

