package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.model.dto.PmsProductConditionDTO;
import pers.brian.mall.modules.pms.model.dto.ProductSaveParamsDTO;
import pers.brian.mall.modules.pms.model.dto.ProductUpdateInitDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProduct;

import java.util.List;

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

    /**
     * 更新商品状态
     *
     * @param status    将要更新的状态
     * @param ids       待更新的商品列表
     * @param getStatus 待更新的字段
     * @return 是否更新成功
     */
    boolean updateStatus(Integer status, List<Long> ids, SFunction<PmsProduct, ?> getStatus);

    /**
     * 根据提供的参数添加商品
     *
     * @param productSaveParamsDTO 提供的参数
     * @return 是否添加成功
     */
    boolean create(ProductSaveParamsDTO productSaveParamsDTO);

    /**
     * 通过请求的参数更新商品
     *
     * @param productSaveParamsDTO 请求的参数信息
     * @return 是否更新成功
     */
    boolean update(ProductSaveParamsDTO productSaveParamsDTO);
}
