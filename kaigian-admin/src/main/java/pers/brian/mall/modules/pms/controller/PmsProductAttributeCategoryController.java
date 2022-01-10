package pers.brian.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.model.dto.PmsProductAttributeCategoryDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttributeCategory;
import pers.brian.mall.modules.pms.service.PmsProductAttributeCategoryService;

import java.util.List;

/**
 * 产品属性分类表 前端控制器
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeCategory>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<PmsProductAttributeCategory> productAttributeCategoryPage = productAttributeCategoryService.page(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(productAttributeCategoryPage));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> create(PmsProductAttributeCategory productAttributeCategory) {
        boolean saved = productAttributeCategoryService.customSave(productAttributeCategory);
        if (saved) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        boolean deleted = productAttributeCategoryService.removeById(id);
        if (deleted) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(PmsProductAttributeCategory productAttributeCategory) {
        boolean updated = productAttributeCategoryService.updateById(productAttributeCategory);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductAttributeCategoryDTO>> getListWithAttr() {
        List<PmsProductAttributeCategoryDTO> list = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }
}

