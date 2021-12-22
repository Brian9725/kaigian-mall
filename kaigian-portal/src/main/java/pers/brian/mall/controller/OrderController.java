package pers.brian.mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.common.exception.ApiException;
import pers.brian.mall.component.trade.constant.PayTypeEnum;
import pers.brian.mall.component.trade.service.TradeService;
import pers.brian.mall.dto.ConfirmOrderDTO;
import pers.brian.mall.dto.OrderDetailDTO;
import pers.brian.mall.dto.OrderListDTO;
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

    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "/generateConfirmOrder", method = RequestMethod.POST)
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

    @RequestMapping(value = "/list/userOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<IPage<OrderListDTO>> getMyOrders(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<OrderListDTO> myOrders = orderService.getMyOrders(pageSize, pageNum);
        return CommonResult.success(myOrders);
    }

    @ApiOperation("支付接口，只实现支付宝支付，微信支付暂未实现")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id"),
            @ApiImplicitParam(name = "payType", value = "支付类型1:支付宝2：微信", allowableValues = "1,2", dataType = "integer")})
    @RequestMapping(value = "/tradeQrCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> tradeQrCode(@RequestParam("orderId") Long orderId, @RequestParam("payType") Integer payType) {
        PayTypeEnum payTypeEnum = PayTypeEnum.getPayTypeEnum(payType);
        if (payTypeEnum == null) {
            throw new ApiException("支付类型参数错误！");
        }
        return tradeService.tradeQrCode(orderId, payTypeEnum);
    }
}
