package pers.brian.mall.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
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
import pers.brian.mall.component.trade.alipay.config.Configs;
import pers.brian.mall.component.trade.constant.PayTypeEnum;
import pers.brian.mall.component.trade.service.TradeService;
import pers.brian.mall.dto.ConfirmOrderDTO;
import pers.brian.mall.dto.OrderDetailDTO;
import pers.brian.mall.dto.OrderListDTO;
import pers.brian.mall.dto.OrderParamDTO;
import pers.brian.mall.modules.oms.model.OmsOrder;
import pers.brian.mall.modules.oms.service.OmsOrderService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        String tradeQrCode = tradeService.tradeQrCode(orderId, payTypeEnum);
        return StrUtil.isEmpty(tradeQrCode) ? CommonResult.failed() : CommonResult.success(tradeQrCode);
    }

    @RequestMapping(value = "/paySuccess/{payType}", method = RequestMethod.POST)
    public void getMyOrders(@PathVariable("payType") Integer payType, HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>(16);
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            if (!"sign_type".equalsIgnoreCase(name)) {
                String[] values = requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? (valueStr + values[i]) : (valueStr + values[i] + ",");
                }
                // 乱码解决，这段代码在出现乱码时使用
                // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
        }

        // 如果Configs类没有初始化则初始化
        if (Configs.getConfigs() == null) {
            Configs.init("zfbinfo.properties");
        }
        String alipayPublicKey = Configs.getConfigs().getString("alipay_public_key");

        // 验签:去除sign和sign_type参数进行验签，checkV1会在方法中去除，checkV2不会去除sign_type，所以要手动排除
        // 调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV2(params, alipayPublicKey, "utf-8", "RSA2");

        // ——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            // 订单id
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            if (StrUtil.isNotBlank(outTradeNo) && NumberUtil.isNumber(outTradeNo)) {
                Long orderId = Long.parseLong(outTradeNo);
                orderService.paySuccess(orderId, payType);
                log.info("支付成功");
            }
        } else {
            log.info("验签失败");
        }
    }
}
