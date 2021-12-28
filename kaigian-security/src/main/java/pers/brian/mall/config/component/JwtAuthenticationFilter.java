package pers.brian.mall.config.component;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import pers.brian.mall.common.util.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: JWT过滤器
 * @Author: BrianHu
 * @Create: 2021-12-27 10:58
 * @Version: 0.0.1
 **/
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 拿到jwt令牌
        String jwt = request.getHeader(tokenHeader);
        //判断是否存在 判断开头是否加了tokenHead Bearer
        if (!StrUtil.isBlank(jwt) && jwt.startsWith(tokenHead)) {
            // 解密
            jwt = jwt.substring(tokenHead.length());
            String userName = jwtTokenUtil.getUserNameFromToken(jwt);

            if (!StrUtil.isBlank(userName)) {
                // 从服务器中查询
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if (userDetails != null) {
                    // 生成spring-security的通过认证标识
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
