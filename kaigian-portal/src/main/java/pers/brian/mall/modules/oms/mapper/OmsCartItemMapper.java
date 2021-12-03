package pers.brian.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import pers.brian.mall.dto.CartItemStockDTO;
import pers.brian.mall.modules.oms.model.OmsCartItem;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
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
}
