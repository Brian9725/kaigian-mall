package pers.brian.mall.modules.ums.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.ums.model.UmsResourceCategory;
import pers.brian.mall.modules.ums.service.UmsResourceCategoryService;

import java.util.List;

/**
 * @Description: 后台资源分类管理Controller
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Controller
@Api(tags = "UmsResourceCategoryController")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {

    private final UmsResourceCategoryService resourceCategoryService;

    @Autowired
    public UmsResourceCategoryController(UmsResourceCategoryService resourceCategoryService) {
        this.resourceCategoryService = resourceCategoryService;
    }

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsResourceCategory>> listAll() {
        List<UmsResourceCategory> resourceList = resourceCategoryService.listAll();
        return CommonResult.success(resourceList);
    }

    @ApiOperation("添加后台资源分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> create(@RequestBody UmsResourceCategory umsResourceCategory) {
        boolean success = resourceCategoryService.create(umsResourceCategory);
        if (success) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@PathVariable Long id,
                                        @RequestBody UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        boolean success = resourceCategoryService.updateById(umsResourceCategory);
        if (success) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@PathVariable Long id) {
        boolean success = resourceCategoryService.removeById(id);
        if (success) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }
}
