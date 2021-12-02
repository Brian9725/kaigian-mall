package pers.brian.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.dto.HomeGoodsSaleDTO;
import pers.brian.mall.dto.HomeMenusBannerDTO;
import pers.brian.mall.dto.HomeMenusDTO;
import pers.brian.mall.modules.pms.service.PmsProductCategoryService;
import pers.brian.mall.modules.sms.model.SmsHomeAdvertise;
import pers.brian.mall.modules.sms.service.SmsHomeAdvertiseService;
import pers.brian.mall.modules.sms.service.SmsHomeCategoryService;

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

    @Autowired
    private SmsHomeAdvertiseService homeAdvertiseService;

    @Autowired
    private SmsHomeCategoryService homeCategoryService;

    @RequestMapping(value = "/menus_banner", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<HomeMenusBannerDTO> getMenus() {
        // 分类导航
        List<HomeMenusDTO> list = productCategoryService.getMenus();
        // banner
        List<SmsHomeAdvertise> homeAdvertisesList = homeAdvertiseService.getHomeBanners();

        HomeMenusBannerDTO homeMenusBannerDTO = new HomeMenusBannerDTO();
        homeMenusBannerDTO.setHomeMenusList(list);
        homeMenusBannerDTO.setHomeAdvertisesList(homeAdvertisesList);

        return CommonResult.success(homeMenusBannerDTO);
    }

    @RequestMapping(value = "/goods_sale", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<HomeGoodsSaleDTO>> getGoodsSale() {
        List<HomeGoodsSaleDTO> list = homeCategoryService.getGoodsSale();
        return CommonResult.success(list);
    }
}
