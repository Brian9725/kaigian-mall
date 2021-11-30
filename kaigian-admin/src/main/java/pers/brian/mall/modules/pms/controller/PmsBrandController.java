package pers.brian.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.model.entity.PmsBrand;
import pers.brian.mall.modules.pms.service.PmsBrandService;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    private final PmsBrandService brandService;

    @Autowired
    public PmsBrandController(PmsBrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> list(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        // TODO:使用Params类接收请求参数，返回VO类
        Page<PmsBrand> brandPage = brandService.page(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandPage));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> getBrand(@PathVariable(value = "id") Long id) {
        return CommonResult.success(brandService.getById(id));
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> createBrand(@RequestBody PmsBrand brand) {
        boolean saved = brandService.save(brand);
        if (saved) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateShowStatus(@RequestParam(value = "showStatus", defaultValue = "0") Integer showStatus,
                                                  @RequestParam(value = "ids") List<Long> ids) {
        Boolean updated = brandService.updateShowStatus(showStatus, ids);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateFactoryStatus(@RequestParam(value = "factoryStatus", defaultValue = "0") Integer factoryStatus,
                                                     @RequestParam(value = "ids") List<Long> ids) {
        Boolean updated = brandService.updateFactoryStatus(factoryStatus, ids);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> deleteBrand(@PathVariable(value = "id") Long id) {
        boolean removed = brandService.removeById(id);
        if (removed) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@PathVariable(value = "id") Long id,
                                        @RequestBody PmsBrand brand) {
        boolean updated = brandService.updateById(brand);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }
}

