package pers.brian.mall.modules.oms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.oms.model.entity.OmsOrderSetting;
import pers.brian.mall.modules.oms.service.OmsOrderSettingService;

/**
 * 订单设置表 前端控制器
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@RestController
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {

    @Autowired
    private OmsOrderSettingService orderSettingService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrderSetting> getOrderSetting(@PathVariable("id") Long id) {
        return CommonResult.success(orderSettingService.getById(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateOrderSetting(@PathVariable("id") Long id) {
        // TODO:
        return null;
    }

}

