package pers.brian.mall.modules.oms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.oms.model.dto.OmsOrderConditionDTO;
import pers.brian.mall.modules.oms.model.entity.OmsOrder;
import pers.brian.mall.modules.oms.service.OmsOrderService;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/order")
public class OmsOrderController {

    @Autowired
    private OmsOrderService orderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderConditionDTO conditionDTO) {
        Page<OmsOrder> orderPage = orderService.list(conditionDTO);
        return CommonResult.success(CommonPage.restPage(orderPage));
    }

    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> closeOrder() {
        // TODO:
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> deleteOrder() {
        return null;
    }

    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> deliveryOrder() {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrder> getOrderDetail(@PathVariable("id") Long id) {
        OmsOrder order = orderService.getById(id);
        return CommonResult.success(order);
    }

    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateReceiverInfo() {
        return null;
    }

    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateMoneyInfo() {
        return null;
    }

    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateOrderNote() {
        return null;
    }
}

