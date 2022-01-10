package pers.brian.mall.component.trade.alipay.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author BrianHu
 * @create 2021-12-22 17:43
 **/
@Data
@Component
@ConfigurationProperties(prefix = "trade.zhifu.qrcode")
public class TradePayProp {
    private String paySuccessCallBack;

    private String storePath;

    private String httpBasePath;
}
