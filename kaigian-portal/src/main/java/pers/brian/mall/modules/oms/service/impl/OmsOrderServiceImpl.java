package pers.brian.mall.modules.oms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.common.api.ResultCode;
import pers.brian.mall.common.exception.ApiException;
import pers.brian.mall.dto.ConfirmOrderDTO;
import pers.brian.mall.modules.oms.mapper.OmsOrderMapper;
import pers.brian.mall.modules.oms.model.OmsCartItem;
import pers.brian.mall.modules.oms.model.OmsOrder;
import pers.brian.mall.modules.oms.service.OmsCartItemService;
import pers.brian.mall.modules.oms.service.OmsOrderService;
import pers.brian.mall.modules.pms.model.PmsProduct;
import pers.brian.mall.modules.pms.service.PmsProductService;
import pers.brian.mall.modules.ums.model.UmsMember;
import pers.brian.mall.modules.ums.model.UmsMemberReceiveAddress;
import pers.brian.mall.modules.ums.service.UmsMemberReceiveAddressService;
import pers.brian.mall.modules.ums.service.UmsMemberService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {

    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private OmsCartItemService cartItemService;

    @Autowired
    private UmsMemberReceiveAddressService addressService;

    @Autowired
    private PmsProductService productService;

    @Override
    public ConfirmOrderDTO generateConfirmOrder(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        ConfirmOrderDTO confirmOrderDTO = new ConfirmOrderDTO();
        // 1.商品
        List<OmsCartItem> omsCartItemsList = cartItemService.listByIds(ids);
        confirmOrderDTO.setCartList(omsCartItemsList);

        // 2、计算价格
        calcCatAmount(confirmOrderDTO);
        // 3.收货地址
        UmsMember currentMember = memberService.getCurrentMember();
        QueryWrapper<UmsMemberReceiveAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId());
        List<UmsMemberReceiveAddress> addressList = addressService.list(queryWrapper);
        confirmOrderDTO.setAddressList(addressList);

        return confirmOrderDTO;
    }

    /**
     * 计算价格
     *
     * @param confirmOrderDTO 需要计算价格的订单
     */
    private void calcCatAmount(ConfirmOrderDTO confirmOrderDTO) {
        //计算商品数量
        Integer productTotal = 0;
        // 总价
        BigDecimal priceTotal = new BigDecimal(0);
        // 运费
        BigDecimal freightAmount = new BigDecimal(0);

        for (OmsCartItem omsCartItem : confirmOrderDTO.getCartList()) {
            // 商品总件数
            productTotal += omsCartItem.getQuantity();
            // 总价
            priceTotal = priceTotal.add(omsCartItem.getPrice().multiply(new BigDecimal(omsCartItem.getQuantity())));

            // TODO:将是否包邮冗余到cartItem中，避免这里查数据库耗费性能
            PmsProduct product = productService.getById(omsCartItem.getProductId());
            String serviceIds = product.getServiceIds();
            String[] serviceIdsArray = serviceIds.split(",");
            if (serviceIdsArray.length > 0) {
                // 判断是否包邮，默认运费是10元
                if (!ArrayUtil.containsAny(serviceIdsArray, "3")) {
                    freightAmount = freightAmount.add(new BigDecimal(10));
                }
            }
        }
        confirmOrderDTO.setProductTotal(productTotal);
        confirmOrderDTO.setPriceTotal(priceTotal);
        confirmOrderDTO.setFreightAmount(freightAmount);
        confirmOrderDTO.setPayAmount(priceTotal.subtract(freightAmount));
    }
}
