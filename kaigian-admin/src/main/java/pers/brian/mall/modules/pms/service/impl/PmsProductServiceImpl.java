package pers.brian.mall.modules.pms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.brian.mall.modules.pms.mapper.PmsProductMapper;
import pers.brian.mall.modules.pms.model.dto.PmsProductConditionDTO;
import pers.brian.mall.modules.pms.model.dto.ProductSaveParamsDTO;
import pers.brian.mall.modules.pms.model.dto.ProductUpdateInitDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProduct;
import pers.brian.mall.modules.pms.service.*;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    private final PmsProductMapper productMapper;

    private final PmsMemberPriceService memberPriceService;

    private final PmsProductLadderService productLadderService;

    private final PmsProductFullReductionService productFullReductionService;

    private final PmsSkuStockService skuStockService;

    private final PmsProductAttributeValueService productAttributeValueService;

    @Autowired
    public PmsProductServiceImpl(PmsProductMapper productMapper,
                                 PmsMemberPriceService memberPriceService,
                                 PmsProductLadderService productLadderService,
                                 PmsProductFullReductionService productFullReductionService,
                                 PmsSkuStockService skuStockService,
                                 PmsProductAttributeValueService productAttributeValueService) {
        this.productMapper = productMapper;
        this.memberPriceService = memberPriceService;
        this.productLadderService = productLadderService;
        this.productFullReductionService = productFullReductionService;
        this.skuStockService = skuStockService;
        this.productAttributeValueService = productAttributeValueService;
    }

    @Override
    public Page<PmsProduct> list(PmsProductConditionDTO condition) {
        Page<PmsProduct> page = new Page<>(condition.getPageNum(), condition.getPageSize());

        LambdaQueryWrapper<PmsProduct> lambdaWrapper = new QueryWrapper<PmsProduct>().lambda();
        // 商品名称
        if (!StrUtil.isBlank(condition.getKeyword())) {
            lambdaWrapper.like(PmsProduct::getName, condition.getKeyword());
        }
        // 商品货号
        if (!StrUtil.isBlank(condition.getProductSn())) {
            lambdaWrapper.eq(PmsProduct::getProductSn, condition.getProductSn());
        }

        // 商品分类
        if (condition.getProductCategoryId() != null && condition.getProductCategoryId() > 0) {
            lambdaWrapper.eq(PmsProduct::getProductCategoryId, condition.getProductCategoryId());
        }
        // 商品品牌
        if (condition.getBrandId() != null && condition.getBrandId() > 0) {
            lambdaWrapper.eq(PmsProduct::getBrandId, condition.getBrandId());
        }
        // 上架状态
        if (condition.getPublishStatus() != null) {
            lambdaWrapper.eq(PmsProduct::getPublishStatus, condition.getPublishStatus());
        }
        // 审核状态
        if (condition.getVerifyStatus() != null) {
            lambdaWrapper.eq(PmsProduct::getVerifyStatus, condition.getVerifyStatus());
        }
        lambdaWrapper.orderByAsc(PmsProduct::getSort);
        return this.page(page, lambdaWrapper);
    }

    @Override
    public boolean updateStatus(Integer status, List<Long> ids, SFunction<PmsProduct, ?> getStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(getStatus, status)
                .in(PmsProduct::getId, ids);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean create(ProductSaveParamsDTO productSaveParamsDTO) {
        // 1.保存商品基本信息 --商品主表
        productSaveParamsDTO.setId(null);
        boolean result = this.save(productSaveParamsDTO);
        if (result) {
            // 为了解决，前端会传入其他促销方式的空数据进来
            switch (productSaveParamsDTO.getPromotionType()) {
                case 2:
                    // 2.会员价格
                    saveRelatedData(productSaveParamsDTO.getMemberPriceList(), productSaveParamsDTO.getId(), memberPriceService);
                    break;
                case 3:
                    // 3.阶梯价格
                    saveRelatedData(productSaveParamsDTO.getProductLadderList(), productSaveParamsDTO.getId(), productLadderService);
                    break;
                case 4:
                    // 4.减满价格
                    saveRelatedData(productSaveParamsDTO.getProductFullReductionList(), productSaveParamsDTO.getId(), productFullReductionService);
                    break;
                default:
                    break;
            }
            // 5.sku
            saveRelatedData(productSaveParamsDTO.getSkuStockList(), productSaveParamsDTO.getId(), skuStockService);
            // 6.spu
            saveRelatedData(productSaveParamsDTO.getProductAttributeValueList(), productSaveParamsDTO.getId(), productAttributeValueService);
        }
        return result;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean update(ProductSaveParamsDTO productSaveParamsDTO) {
        // 1.保存商品基本信息 --商品主表
        boolean result = this.updateById(productSaveParamsDTO);
        if (result) {
            // 为了解决，前端会传入其他促销方式的空数据进来
            switch (productSaveParamsDTO.getPromotionType()) {
                case 2:
                    // 根据商品id删除
                    deleteRelatedDataByProductId(productSaveParamsDTO.getId(), memberPriceService);
                    // 2.会员价格
                    saveRelatedData(productSaveParamsDTO.getMemberPriceList(), productSaveParamsDTO.getId(), memberPriceService);
                    break;
                case 3:
                    // 根据商品id删除
                    deleteRelatedDataByProductId(productSaveParamsDTO.getId(), productLadderService);
                    // 3.阶梯价格
                    saveRelatedData(productSaveParamsDTO.getProductLadderList(), productSaveParamsDTO.getId(), productLadderService);
                    break;
                case 4:
                    // 根据商品id删除
                    deleteRelatedDataByProductId(productSaveParamsDTO.getId(), productFullReductionService);
                    // 4.减满价格
                    saveRelatedData(productSaveParamsDTO.getProductFullReductionList(), productSaveParamsDTO.getId(), productFullReductionService);
                    break;
                default:
                    break;
            }
            // 根据商品id删除
            deleteRelatedDataByProductId(productSaveParamsDTO.getId(), skuStockService);
            // 5.sku
            saveRelatedData(productSaveParamsDTO.getSkuStockList(), productSaveParamsDTO.getId(), skuStockService);
            // 根据商品id删除
            deleteRelatedDataByProductId(productSaveParamsDTO.getId(), productAttributeValueService);
            // 6.spu
            saveRelatedData(productSaveParamsDTO.getProductAttributeValueList(), productSaveParamsDTO.getId(), productAttributeValueService);
        }
        return result;
    }

    @Override
    public ProductUpdateInitDTO getUpdateInfo(Long id) {
        return productMapper.getUpdateInfo(id);
    }

    /**
     * 根据商品id删除关联数据
     *
     * @param productId 商品id
     * @param service   相关联的服务
     * @param <T>       关联数据的类型
     */
    private <T> void deleteRelatedDataByProductId(Long productId, IService<T> service) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        service.remove(queryWrapper);
    }

    /**
     * 公共方法： 保存会员价格、阶梯价格、减满价格、 sku 、 spu 商品的关联数据
     * <p>
     * 统一： 都需要设置商品id,  都需要批量保存
     *
     * @param list      需要保存的关联数据列表
     * @param productId 商品id
     * @param service   相关联的服务
     * @param <T>       关联数据的类型
     */
    private <T> void saveRelatedData(List<T> list, Long productId, IService<T> service) {
        // 如果数据为空 或者长度为0  不做任何操作
        if (CollectionUtil.isEmpty(list)) {
            return;
        }

        try {
            // 循环、反射、赋值商品id
            for (T obj : list) {
                Method setProductIdMethod = obj.getClass().getMethod("setProductId", Long.class);
                // 在修改状态清除主键id
                Method setIdMethod = obj.getClass().getMethod("setId", Long.class);
                setIdMethod.invoke(obj, (Long) null);
                // 调用setProductId
                setProductIdMethod.invoke(obj, productId);
            }
            service.saveBatch(list);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
