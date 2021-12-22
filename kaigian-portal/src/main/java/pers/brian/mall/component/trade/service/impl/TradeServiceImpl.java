package pers.brian.mall.component.trade.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.common.exception.ApiException;
import pers.brian.mall.component.trade.constant.PayTypeEnum;
import pers.brian.mall.component.trade.service.TradeService;
import pers.brian.mall.dto.OrderDetailDTO;
import pers.brian.mall.modules.oms.model.OmsOrderSetting;
import pers.brian.mall.modules.oms.service.OmsOrderService;
import pers.brian.mall.modules.oms.service.OmsOrderSettingService;

import java.util.Date;

/**
 * @Description: 支付服务实现类
 * @Author: BrianHu
 * @Create: 2021-12-22 11:28
 * @Version: 0.0.1
 **/
@Service
@Slf4j
public class TradeServiceImpl implements TradeService {

    @Autowired
    private OmsOrderService orderService;

    @Autowired
    private OmsOrderSettingService orderSettingService;

    @Override
    public CommonResult<String> tradeQrCode(Long orderId, PayTypeEnum payTypeEnum) {
        // 订单信息
        OrderDetailDTO detail = orderService.getOrderDetail(orderId);
        if (detail == null) {
            throw new IllegalArgumentException("订单id参数异常！");
        }
        // 判断当前订单是否是待支付、是否超过订单的支付时间
        if (detail.getStatus() != 0) {
            throw new ApiException("订单异常！无法支付");
        }
        // 下单时间
        Date createTime = detail.getCreateTime();
        // 当前时间
        Date now = new Date();

        // 1.获取规定的时间
        OmsOrderSetting orderSetting = orderSettingService.getById(1L);
        // 普通订单的超时分钟
        Integer overtime = orderSetting.getNormalOrderOvertime();

        long between = DateUtil.between(createTime, now, DateUnit.MINUTE);
        if (between > overtime) {
            throw new ApiException("订单超时未支付，请重新下单!");
        }
        String qrCodePath = null;
        switch (payTypeEnum) {
            case ALI_PAY:
                qrCodePath = aliPayTrade(detail);
                break;
            case WECHAT_PAY:
                qrCodePath = weChatPayTrade(detail);
                break;
            default:
                break;
        }
        return StrUtil.isEmpty(qrCodePath) ? CommonResult.failed() : CommonResult.success(qrCodePath);
    }


    /**
     * 支付宝支付
     *
     * @param detail 订单详情
     * @return 支付用的二维码图片路径
     */
    private String aliPayTrade(OrderDetailDTO detail) {
        return null;
    }


    /**
     * 微信支付（暂不实现)
     *
     * @param detail 订单详情
     * @return 支付用的二维码图片路径
     */
    private String weChatPayTrade(OrderDetailDTO detail) {
        return null;
    }
}
