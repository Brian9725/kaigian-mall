package pers.brian.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.brian.mall.dto.CartItemStockDTO;
import pers.brian.mall.modules.oms.model.OmsCartItem;

import java.util.List;

/**
 * 购物车表 Mapper 接口
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Component
public interface OmsCartItemMapper extends BaseMapper<OmsCartItem> {

    /**
     * 通过用户id获取购物车数据
     *
     * @param id 用户id
     * @return 用户的购物车数据
     */
    List<CartItemStockDTO> getCartItemStock(Long id);

    /**
     * 自定义xml查询，结合mybatis-plus的wrapper
     *
     * @param ew 封装的wrapper
     * @return 购物车货品信息
     */
    List<CartItemStockDTO> getCartItemStockByIds(@Param(Constants.WRAPPER) QueryWrapper<OmsCartItem> ew);
}
