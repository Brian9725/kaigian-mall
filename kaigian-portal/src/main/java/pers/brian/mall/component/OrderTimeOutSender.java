package pers.brian.mall.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.brian.mall.modules.oms.service.OmsOrderService;

/**
 * @Description: 订单超时取消并解锁库存的定时器o
 * @Author: BrianHu
 * @Create: 2021-12-08 11:17
 * @Version: 0.0.1
 **/
@Component
@Slf4j
public class OrderTimeOutSender {

    @Autowired
    private OmsOrderService orderService;

    @Scheduled(cron = "0 0/50 * ? * ?")
    private void cancelOverTimeOrder() {
        log.info("--------OrderTimeOutSender订单超时取消并解锁库存的定时器开始----------");
        orderService.cancelOverTimeOrder();
        log.info("--------OrderTimeOutSender订单超时取消并解锁库存的定时器结束----------");
    }
}
