package pers.brian.mall.modules.ums.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.brian.mall.common.exception.ApiException;
import pers.brian.mall.common.exception.Asserts;
import pers.brian.mall.domain.MemberDetails;
import pers.brian.mall.modules.ums.mapper.UmsMemberLoginLogMapper;
import pers.brian.mall.modules.ums.mapper.UmsMemberMapper;
import pers.brian.mall.modules.ums.model.UmsMember;
import pers.brian.mall.modules.ums.model.UmsMemberLoginLog;
import pers.brian.mall.modules.ums.service.UmsMemberCacheService;
import pers.brian.mall.modules.ums.service.UmsMemberService;

import java.util.Date;
import java.util.List;

/**
 * 会员表 服务实现类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    private UmsMemberCacheService memberCacheService;

    @Autowired
    private UmsMemberLoginLogMapper loginLogMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        //密码需要客户端加密后传递
        UmsMember member = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            member = ((MemberDetails) userDetails).getUmsMember();

            if (!passwordEncoder.matches(password, member.getPassword())) {
                Asserts.fail("密码不正确");
            }

            // 生成spring-security的通过认证标识
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            if (!userDetails.isEnabled()) {
                Asserts.fail("帐号已被禁用");
            }
            insertLoginLog(username);
        } catch (Exception e) {
            Asserts.fail("登录异常:" + e.getMessage());
        }
        return member;
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsMember user = getMemberByUsername(username);
        if (user == null) {
            return;
        }
        UmsMemberLoginLog loginLog = new UmsMemberLoginLog();
        loginLog.setMemberId(user.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ip = (attributes == null) ? "" : attributes.getRequest().getRemoteAddr();
        loginLog.setIp(ip);
        loginLogMapper.insert(loginLog);
    }

    @Override
    public UmsMember getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        return memberDetails.getUmsMember();
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

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember umsMember = getMemberByUsername(username);
        if (umsMember != null) {
            return new MemberDetails(umsMember);
        }
        throw new ApiException("用户名或密码错误!");
    }
}
