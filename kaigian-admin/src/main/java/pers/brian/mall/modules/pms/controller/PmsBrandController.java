package pers.brian.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.model.PmsBrand;
import pers.brian.mall.modules.pms.model.PmsProductAttributeCategory;
import pers.brian.mall.modules.pms.service.PmsBrandService;

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

    /**
     * 获取品牌管理列表
     *
     * @param keyword  关键词
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 品牌管理列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> list(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<PmsBrand> brandPage = brandService.page(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandPage));
    }
}

