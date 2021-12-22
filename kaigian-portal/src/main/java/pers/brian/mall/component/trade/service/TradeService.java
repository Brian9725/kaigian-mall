package pers.brian.mall.component.trade.service;

import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.component.trade.constant.PayTypeEnum;

/**
 * @Description: 支付服务接口
 * @Author: BrianHu
 * @Create: 2021-12-22 11:27
 * @Version: 0.0.1
 **/
public interface TradeService {
    /**
     * 生成当面付二维码
     *
     * @param orderId     订单id
     * @param payTypeEnum 支付方式
     * @return 支付二维码路径
     */
    CommonResult<String> tradeQrCode(Long orderId, PayTypeEnum payTypeEnum);
}
