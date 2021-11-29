package pers.brian.mall.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.brian.mall.modules.pms.model.dto.PmsProductConditionDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProduct;
import pers.brian.mall.modules.pms.mapper.PmsProductMapper;
import pers.brian.mall.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
