package pers.brian.mall.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.dto.ConfirmOrderDTO;
import pers.brian.mall.dto.OrderDetailDTO;
import pers.brian.mall.dto.OrderParamDTO;
import pers.brian.mall.modules.oms.model.OmsOrder;
import pers.brian.mall.modules.oms.service.OmsOrderService;

import java.util.List;

/**
 * @Description: 订单相关的服务
 * @Author: BrianHu
 * @Create: 2021-12-03 16:45
 * @Version: 0.0.1
 **/
@RestController
@Api(tags = "OrderController")
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OmsOrderService orderService;

    @RequestMapping(value = "generateConfirmOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ConfirmOrderDTO> generateConfirmOrder(@RequestParam("itemIds") List<Long> ids) {
        ConfirmOrderDTO confirmOrderDTO = orderService.generateConfirmOrder(ids);
        return CommonResult.success(confirmOrderDTO);
    }

    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Long> generateOrder(@RequestBody OrderParamDTO paramDTO) {
        OmsOrder omsOrder = orderService.generateOrder(paramDTO);
        return CommonResult.success(omsOrder.getId());
    }

    @RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OrderDetailDTO> getOrderDetail(@RequestParam("orderId") Long id) {
        OrderDetailDTO dto = orderService.getOrderDetail(id);
        return CommonResult.success(dto);
    }
}
