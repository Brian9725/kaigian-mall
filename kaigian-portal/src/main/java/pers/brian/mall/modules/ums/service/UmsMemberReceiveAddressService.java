package pers.brian.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.ums.model.UmsMemberReceiveAddress;

import java.util.List;

/**
 * 会员收货地址表 服务类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
public interface UmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddress> {

    /**
     * 添加收货地址
     *
     * @param address 地址信息
     * @return 是否添加成功
     */
    Boolean add(UmsMemberReceiveAddress address);

    /**
     * 编辑收货地址
     *
     * @param address 地址信息
     * @return 是否编辑成功
     */
    Boolean edit(UmsMemberReceiveAddress address);

    /**
     * 删除收货地址
     *
     * @param id 地址id
     * @return 是否删除成功
     */
    Boolean delete(Long id);

    /**
     * 根据用户id获取收货地址列表
     *
     * @return 地址列表
     */
    List<UmsMemberReceiveAddress> listByMemberId();
}
