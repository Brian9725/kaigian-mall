package pers.brian.mall.interceptor;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pers.brian.mall.common.api.ResultCode;
import pers.brian.mall.common.exception.ApiException;
import pers.brian.mall.common.util.JwtTokenUtil;
import pers.brian.mall.modules.ums.model.UmsMember;
import pers.brian.mall.modules.ums.service.UmsMemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
public class AuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 配置文件中的白名单secure.ignored.urls
     */
    private List<String> urls;

    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.不需要登录就可以访问的路径——白名单
        // 获取当前请求   /admin/login
        String requestURI = request.getRequestURI();
        // Ant方式路径匹配 /**  ？  _
        PathMatcher matcher = new AntPathMatcher();
        // 因为session基于cookie,解决cookie的跨站不能共享的新特性问题（课后同学反馈所加，很多同学浏览器中有这个新特性）
        response.setHeader("SET-COOKIE", "JSESSIONID=" + request.getSession().getId() + ";Path=/;secure;HttpOnly;SameSite=None");
        for (String ignoredUrl : urls) {
            if (matcher.match(ignoredUrl, requestURI)) {
                return true;
            }
        }

        // 获取jwt
        String jwt = request.getHeader(tokenHeader);
        // 判断jwt是否为空以及是否以tokenHead开头
        if (StrUtil.isBlank(jwt) || !jwt.startsWith(tokenHead)) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        // jwt解密
        String userName = jwtTokenUtil.getUserNameFromToken(jwt.substring(tokenHead.length()));
        if (StrUtil.isBlank(userName)) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }

        // 获取登陆用户信息
        UmsMember member = memberService.getMemberByUsername(userName);
        if (member == null) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        JwtTokenUtil.currentUserName.set(userName);
        return true;
    }


    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
