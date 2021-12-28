package pers.brian.mall.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pers.brian.mall.modules.ums.model.UmsMember;

import java.util.Collection;

/**
 * @Description: 用户信息、用户权限信息
 * @Author: BrianHu
 * @Create: 2021-12-27 11:31
 * @Version: 0.0.1
 **/
public class MemberDetails implements UserDetails {

    private static final long serialVersionUID = 9005711755277448534L;

    /**
     * 用户信息
     */
    private UmsMember umsMember;

    public MemberDetails(UmsMember umsMember) {
        this.umsMember = umsMember;
    }

    /**
     * 前台除了白名单 没有其他的权限资源
     *
     * @return 资源集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return umsMember.getPassword();
    }

    @Override
    public String getUsername() {
        return umsMember.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 用户是否启用状态
     *
     * @return 是否启用
     */
    @Override
    public boolean isEnabled() {
        return umsMember.getStatus() == 1;
    }

    public UmsMember getUmsMember() {
        return umsMember;
    }
}
