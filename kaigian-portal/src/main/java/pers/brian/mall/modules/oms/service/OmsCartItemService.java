package pers.brian.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.dto.AddCartDTO;
import pers.brian.mall.dto.CartItemStockDTO;
import pers.brian.mall.modules.oms.model.OmsCartItem;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
public interface OmsCartItemService extends IService<OmsCartItem> {

    /**
     * 添加购物车
     *
     * @param addCartDTO 商品参数
     * @return 是否添加成功
     */
    Boolean add(AddCartDTO addCartDTO);

    /**
     * 获取购物车中商品数量
     *
     * @return 购物车商品数量
     */
    Integer getCarProductSum();

    /**
     * 获取购物车数据
     *
     * @return 购物车数据
     */
    List<CartItemStockDTO> getList();

    /**
     * 更新购物车商品数量
     *
     * @param id       商品id
     * @param quantity 更新的数量
     * @return 更新是否成功
     */
    Boolean updateQuantity(Long id, Integer quantity);

    /**
     * 删除购物车商品
     *
     * @param id 商品id
     * @return 是否删除成功
     */
    Boolean delete(Long id);
}
