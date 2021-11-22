package pers.brian.mall.modules.pms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.dto.ProductAttributeCateDTO;
import pers.brian.mall.modules.pms.service.PmsProductAttributeCategoryService;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    private final PmsProductAttributeCategoryService productAttributeCategoryService;

    @Autowired
    public PmsProductAttributeCategoryController(PmsProductAttributeCategoryService productAttributeCategoryService) {
        this.productAttributeCategoryService = productAttributeCategoryService;
    }

    /**
     * 筛选属性下拉级联数据
     *
     * @return 筛选属性下拉级联数据
     */
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    public CommonResult<List<ProductAttributeCateDTO>> getListWithAttr() {
        List<ProductAttributeCateDTO> list = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }
}

