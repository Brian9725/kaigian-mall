package pers.brian.mall.component.trade.constant;

/**
 * @Description: 支付方式枚举类
 * @Author: BrianHu
 * @Create: 2021-12-22 15:05
 * @Version: 0.0.1
 **/
public enum PayTypeEnum {

    // 支付宝
    ALI_PAY(1, "支付宝"),
    // 微信支付
    WECHAT_PAY(2, "微信支付");

    /**
     * 支付类别
     */
    private Integer payType;

    /**
     * 支付方式描述
     */
    private String desc;

    PayTypeEnum(Integer payType, String desc) {
        this.payType = payType;
        this.desc = desc;
    }

    public Integer getPayType() {
        return payType;
    }

    public String getDesc() {
        return desc;
    }

    public static PayTypeEnum getPayTypeEnum(Integer payType) {
        for (PayTypeEnum value : values()) {
            if (value.getPayType().equals(payType)) {
                return value;
            }
        }
        return null;
    }
}
