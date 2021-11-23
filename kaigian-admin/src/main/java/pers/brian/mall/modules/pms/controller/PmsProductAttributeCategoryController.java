package pers.brian.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.dto.ProductAttributeCateDTO;
import pers.brian.mall.modules.pms.model.PmsProductAttributeCategory;
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
     * 获取商品类型列表
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 商品类型列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeCategory>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<PmsProductAttributeCategory> productAttributeCategoryPage = productAttributeCategoryService.page(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(productAttributeCategoryPage));
    }

    /**
     * 筛选属性下拉级联数据
     *
     * @return 筛选属性下拉级联数据
     */
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ProductAttributeCateDTO>> getListWithAttr() {
        List<ProductAttributeCateDTO> list = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }
}

