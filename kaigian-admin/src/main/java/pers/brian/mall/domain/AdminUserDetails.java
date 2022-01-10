package pers.brian.mall.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pers.brian.mall.modules.ums.model.entity.UmsAdmin;
import pers.brian.mall.modules.ums.model.entity.UmsRole;
import pers.brian.mall.modules.ums.service.UmsAdminService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author BrianHu
 * @create 2021-12-27 17:12
 **/
public class AdminUserDetails implements UserDetails {

    /**
     * 用户信息
     */
    private UmsAdmin umsAdmin;

    private List<UmsRole> roleList;

    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsRole> roleList) {
        this.umsAdmin = umsAdmin;
        this.roleList = roleList;
    }

    public UmsAdmin getUmsAdmin() {
        return umsAdmin;
    }

    /**
     * 返回当前用户的角色信息
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
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

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus() == 1;
    }
}
