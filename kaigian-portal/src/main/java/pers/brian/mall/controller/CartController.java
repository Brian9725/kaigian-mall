package pers.brian.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.dto.AddCartDTO;
import pers.brian.mall.modules.oms.service.OmsCartItemService;

/**
 * @Description: 购物车控制器
 * @Author: BrianHu
 * @Create: 2021-12-02 17:24
 * @Version: 0.0.1
 **/
@RestController
@Api(tags = "CarController")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private OmsCartItemService cartItemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody AddCartDTO addCartDTO) {
        Boolean result = cartItemService.add(addCartDTO);
        if (result) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }
}