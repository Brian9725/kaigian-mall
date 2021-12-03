package pers.brian.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.dto.AddCartDTO;
import pers.brian.mall.modules.oms.model.OmsCartItem;

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
}
