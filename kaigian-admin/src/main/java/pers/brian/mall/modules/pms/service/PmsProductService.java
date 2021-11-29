package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.model.dto.PmsProductConditionDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProduct;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
public interface PmsProductService extends IService<PmsProduct> {

    /**
     * 根据条件查询商品列表
     *
     * @param condition 查询条件
     * @return 符合条件的商品列表
     */
    Page<PmsProduct> list(PmsProductConditionDTO condition);
}
