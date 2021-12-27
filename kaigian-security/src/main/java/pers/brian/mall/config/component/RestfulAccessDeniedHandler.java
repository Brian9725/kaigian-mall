package pers.brian.mall.config.component;

import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.common.api.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 没有权限访问时的响应处理类
 * @Author: BrianHu
 * @Create: 2021-12-27 10:36
 * @Version: 0.0.1
 **/
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 响应403 没有相关权限
        response.getWriter().println(JSONUtil.parse(CommonResult.failed(ResultCode.FORBIDDEN)));
        response.getWriter().flush();
    }
}
