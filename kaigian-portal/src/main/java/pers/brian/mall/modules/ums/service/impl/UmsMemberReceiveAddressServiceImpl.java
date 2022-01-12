package pers.brian.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.ums.mapper.UmsMemberReceiveAddressMapper;
import pers.brian.mall.modules.ums.model.UmsMemberReceiveAddress;
import pers.brian.mall.modules.ums.service.UmsMemberReceiveAddressService;
import pers.brian.mall.modules.ums.service.UmsMemberService;

import java.util.List;

/**
 * 会员收货地址表 服务实现类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Service
public class UmsMemberReceiveAddressServiceImpl extends ServiceImpl<UmsMemberReceiveAddressMapper, UmsMemberReceiveAddress> implements UmsMemberReceiveAddressService {

    @Autowired
    private UmsMemberService memberService;

    @Override
    public Boolean add(UmsMemberReceiveAddress address) {
        Long currentMemberId = memberService.getCurrentMember().getId();
        address.setMemberId(currentMemberId);
        return this.save(address);
    }

    @Override
    public Boolean edit(UmsMemberReceiveAddress address) {
        return this.updateById(address);
    }

    @Override
    public Boolean delete(Long ids) {
        return this.removeById(ids);
    }

    @Override
    public List<UmsMemberReceiveAddress> listByMemberId() {
        QueryWrapper<UmsMemberReceiveAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsMemberReceiveAddress::getMemberId, memberService.getCurrentMember().getId());
        return this.list(queryWrapper);
    }
}
