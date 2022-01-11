package pers.brian.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import pers.brian.mall.dto.OrderDetailDTO;
import pers.brian.mall.dto.OrderListDTO;
import pers.brian.mall.modules.oms.model.OmsOrder;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    /**
     * 根据id获取订单详情
     *
     * @param id 订单id
     * @return 订单详情
     */
    OrderDetailDTO getOrderDetail(Long id);

    /**
     * 根据用户id分页获取订单信息
     *
     * @param page     分页信息
     * @param memberId 用户id
     * @return 订单信息
     */
    IPage<OrderListDTO> getMyOrders(Page<?> page, @Param("memberId") Long memberId);
}
