package pers.brian.mall.modules.oms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.oms.mapper.OmsOrderMapper;
import pers.brian.mall.modules.oms.model.dto.OmsOrderConditionDTO;
import pers.brian.mall.modules.oms.model.entity.OmsOrder;
import pers.brian.mall.modules.oms.service.OmsOrderService;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {

    @Override
    public Page<OmsOrder> list(OmsOrderConditionDTO conditionDTO) {
        Page<OmsOrder> page = new Page<>(conditionDTO.getPageNum(), conditionDTO.getPageSize());

        LambdaQueryWrapper<OmsOrder> lambdaWrapper = new QueryWrapper<OmsOrder>().lambda();
        // 订单号
        if (!StrUtil.isBlank(conditionDTO.getOrderSn())) {
            lambdaWrapper.eq(OmsOrder::getOrderSn, conditionDTO.getOrderSn());
        }
        // 收货人关键字
        if (!StrUtil.isBlank(conditionDTO.getReceiverKeyword())) {
            lambdaWrapper.like(OmsOrder::getReceiverName, conditionDTO.getReceiverKeyword());
        }
        // 订单状态
        if (conditionDTO.getStatus() != null) {
            lambdaWrapper.eq(OmsOrder::getStatus, conditionDTO.getStatus());
        }
        // 订单类型
        if (conditionDTO.getOrderType() != null) {
            lambdaWrapper.eq(OmsOrder::getOrderType, conditionDTO.getOrderType());
        }
        // 订单来源
        if (conditionDTO.getSourceType() != null) {
            lambdaWrapper.eq(OmsOrder::getSourceType, conditionDTO.getSourceType());
        }
        // 创建时间
        if (conditionDTO.getCreateTime() != null) {
            // TODO:
        }
        return this.page(page, lambdaWrapper);
    }
}
