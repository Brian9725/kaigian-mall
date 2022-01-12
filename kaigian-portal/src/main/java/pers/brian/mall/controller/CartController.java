package pers.brian.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.dto.AddCartDTO;
import pers.brian.mall.dto.CartItemStockDTO;
import pers.brian.mall.modules.oms.service.OmsCartItemService;

import java.util.List;

/**
 * 购物车控制器
 *
 * @author BrianHu
 * @create 2021-12-02 17:24
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

    @RequestMapping(value = "/products/sum", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> getCartProductSum() {
        Integer count = cartItemService.getCarProductSum();
        return CommonResult.success(count);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CartItemStockDTO>> getList() {
        List<CartItemStockDTO> list = cartItemService.getList();
        return CommonResult.success(list);
    }

    @RequestMapping(value = "/update/quantity", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateQuantity(@RequestParam Long id, @RequestParam Integer quantity) {
        Boolean updated = cartItemService.updateQuantity(id, quantity);
        if (updated) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestParam Long id) {
        Boolean deleted = cartItemService.delete(id);
        if (deleted) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }
}