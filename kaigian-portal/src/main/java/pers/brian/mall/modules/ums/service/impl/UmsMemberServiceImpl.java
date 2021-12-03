package pers.brian.mall.modules.ums.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.common.exception.Asserts;
import pers.brian.mall.common.util.JwtTokenUtil;
import pers.brian.mall.modules.ums.mapper.UmsMemberMapper;
import pers.brian.mall.modules.ums.model.UmsMember;
import pers.brian.mall.modules.ums.service.UmsMemberCacheService;
import pers.brian.mall.modules.ums.service.UmsMemberService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    private UmsMemberCacheService memberCacheService;

    @Override
    public UmsMember register(UmsMember umsMemberParam) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberParam, umsMember);
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //查询是否有相同用户名的用户
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername, umsMember.getUsername());
        List<UmsMember> userList = this.list(wrapper);
        if (userList.size() > 0) {
            return null;
        }
        // 将密码进行加密操作
        String encodePassword = BCrypt.hashpw(umsMember.getPassword());
        umsMember.setPassword(encodePassword);
        this.save(umsMember);
        return umsMember;
    }

    @Override
    public UmsMember login(String username, String password) {
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername, username);
        UmsMember user = this.getOne(wrapper);
        if (user == null) {
            Asserts.fail("用户名错误");
            return null;
        }
        // 密码需要客户端加密后传递
        if (!BCrypt.checkpw(password, user.getPassword())) {
            Asserts.fail("密码错误");
            return null;
        }
        return user;
    }

    @Override
    public UmsMember getCurrentMember() {
        String userName = JwtTokenUtil.currentUserName.get();
        if (StrUtil.isNotBlank(userName)) {
            return getMemberByUsername(userName);
        }
        return null;
    }

    @Override
    public UmsMember getMemberByUsername(String username) {
        UmsMember user = memberCacheService.getUser(username);
        if (user != null) {
            return user;
        }
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername, username);
        List<UmsMember> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            user = adminList.get(0);
            memberCacheService.setUser(user);
            return user;
        }
        return null;
    }
}
