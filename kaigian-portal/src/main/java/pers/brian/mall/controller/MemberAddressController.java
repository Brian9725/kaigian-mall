package pers.brian.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.ums.model.UmsMemberReceiveAddress;
import pers.brian.mall.modules.ums.service.UmsMemberReceiveAddressService;

import java.util.List;

/**
 * 收货地址服务接口
 *
 * @author BrianHu
 * @create 2021-12-07 11:06
 **/
@RestController
@Api(tags = "MemberAddressController")
@RequestMapping("/member/address")
public class MemberAddressController {

    @Autowired
    private UmsMemberReceiveAddressService addressService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody UmsMemberReceiveAddress address) {
        Boolean added = addressService.add(address);
        if (added) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> edit(@PathVariable Long id, @RequestBody UmsMemberReceiveAddress address) {
        address.setId(id);
        Boolean edited = addressService.edit(address);
        if (edited) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@PathVariable Long id) {
        Boolean deleted = addressService.delete(id);
        if (deleted) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsMemberReceiveAddress>> list() {
        List<UmsMemberReceiveAddress> list = addressService.listByMemberId();
        return CommonResult.success(list);
    }

}

