package pers.brian.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.common.api.ResultCode;
import pers.brian.mall.common.exception.Asserts;
import pers.brian.mall.dto.AddCartDTO;
import pers.brian.mall.dto.CartItemStockDTO;
import pers.brian.mall.modules.oms.mapper.OmsCartItemMapper;
import pers.brian.mall.modules.oms.model.OmsCartItem;
import pers.brian.mall.modules.oms.service.OmsCartItemService;
import pers.brian.mall.modules.pms.model.PmsProduct;
import pers.brian.mall.modules.pms.model.PmsSkuStock;
import pers.brian.mall.modules.pms.service.PmsProductService;
import pers.brian.mall.modules.pms.service.PmsSkuStockService;
import pers.brian.mall.modules.ums.model.UmsMember;
import pers.brian.mall.modules.ums.service.UmsMemberService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements OmsCartItemService {

    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private PmsSkuStockService skuStockService;

    @Autowired
    private PmsProductService productService;

    @Autowired
    private OmsCartItemMapper cartItemMapper;

    @Override
    public Boolean add(AddCartDTO addCartDTO) {
        OmsCartItem omsCartItem = new OmsCartItem();
        BeanUtils.copyProperties(addCartDTO, omsCartItem);
        UmsMember currentMember = memberService.getCurrentMember();
        omsCartItem.setMemberId(currentMember.getId());

        // 判断同一个商品、sku、用户 下是否添加的重复的购物车
        OmsCartItem cartItem = getCartItem(omsCartItem.getProductId(), omsCartItem.getProductSkuId(), omsCartItem.getMemberId());
        // 新增
        if (cartItem == null) {
            omsCartItem.setMemberNickname(currentMember.getNickname());

            // 查询sku
            PmsSkuStock sku = skuStockService.getById(omsCartItem.getProductSkuId());
            if (sku == null) {
                Asserts.fail(ResultCode.VALIDATE_FAILED);
            }
            omsCartItem.setPrice(sku.getPrice())
                    .setSp1(sku.getSp1())
                    .setSp2(sku.getSp2())
                    .setSp3(sku.getSp3())
                    .setProductPic(sku.getPic())
                    .setProductSkuCode(sku.getSkuCode());

            PmsProduct product = productService.getById(omsCartItem.getProductId());
            if (product == null) {
                Asserts.fail(ResultCode.VALIDATE_FAILED);
            }
            omsCartItem.setProductName(product.getName())
                    .setProductBrand(product.getBrandName())
                    .setProductSn(product.getProductSn())
                    .setProductSubTitle(product.getSubTitle())
                    .setProductCategoryId(product.getProductCategoryId())
                    .setCreateDate(new Date())
                    .setModifyDate(new Date());
            return (baseMapper.insert(omsCartItem) > 0);
        }
        // 修改  给商品数量+1
        else {
            cartItem.setQuantity(cartItem.getQuantity() + 1).setModifyDate(new Date());

            UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda()
                    .set(OmsCartItem::getQuantity, cartItem.getQuantity())
                    .set(OmsCartItem::getModifyDate, cartItem.getModifyDate())
                    .eq(OmsCartItem::getId, cartItem.getId());
            return (baseMapper.update(cartItem, updateWrapper) > 0);
        }
    }

    @Override
    public Integer getCarProductSum() {
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        Long memberId = memberService.getCurrentMember().getId();
        // 查询当前用户的购物车商品的数量  1个字段
        queryWrapper.select("sum(quantity) as total").lambda().eq(OmsCartItem::getMemberId, memberId);

        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
        if (list != null && list.size() == 1) {
            Map<String, Object> map = list.get(0);
            if (map != null && map.get("total") != null) {
                return Integer.parseInt(map.get("total").toString());
            }
        }
        return 0;
    }

    @Override
    public List<CartItemStockDTO> getList() {
        // 当前用户
        UmsMember currentMember = memberService.getCurrentMember();
        List<CartItemStockDTO> list = cartItemMapper.getCartItemStock(currentMember.getId());
        return list;
    }

    @Override
    public Boolean updateQuantity(Long id, Integer quantity) {
        UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(OmsCartItem::getQuantity, quantity)
                .eq(OmsCartItem::getId, id);
        return this.update(updateWrapper);
    }

    @Override
    public Boolean delete(Long id) {
        return this.removeById(id);
    }

    /**
     * 判断同一个商品、sku、用户 下是否添加的重复的购物车
     *
     * @param productId 商品id
     * @param skuId     skuId
     * @param memberId  用户id
     * @return 返回符合条件的购物车商品
     */
    private OmsCartItem getCartItem(Long productId, Long skuId, Long memberId) {
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(OmsCartItem::getProductId, productId)
                .eq(OmsCartItem::getProductSkuId, skuId)
                .eq(OmsCartItem::getMemberId, memberId);
        return baseMapper.selectOne(queryWrapper);
    }
}
