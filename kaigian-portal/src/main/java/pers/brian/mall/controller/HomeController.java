package pers.brian.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.dto.HomeMenusDTO;
import pers.brian.mall.modules.pms.service.PmsProductCategoryService;

import java.util.List;

/**
 * @Description: 首页控制器
 * @Author: BrianHu
 * @Create: 2021-11-30 16:07
 * @Version: 0.0.1
 **/
@RestController
@Api(tags = "HomeController")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PmsProductCategoryService productCategoryService;

    /**
     * 获取首页类型导航栏和数据
     * get("/home/menus")
     */
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public CommonResult getMenus() {
        // 分类导航
        List<HomeMenusDTO> list = productCategoryService.getMenus();
        return CommonResult.success(list);
    }
}
