package pers.brian.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.common.constant.ComConstants;
import pers.brian.mall.common.util.JwtTokenUtil;
import pers.brian.mall.modules.ums.model.UmsMember;
import pers.brian.mall.modules.ums.service.UmsMemberService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 前台用户服务接口
 * @Author: BrianHu
 * @Create: 2021-12-02 16:17
 * @Version: 0.0.1
 **/
@Controller
@Api(tags = "UserController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private HttpSession session;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsMember> register(@Validated @RequestBody UmsMember umsMemberParam) {
        UmsMember user = memberService.register(umsMemberParam);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, String>> login(@Validated UmsMember umsMemberParam) {
        UmsMember loginUser = memberService.login(umsMemberParam.getUsername(), umsMemberParam.getPassword());
        if (loginUser == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        session.setAttribute(ComConstants.FRONT_CURRENT_USER, loginUser);

        // jwt加密
        String token = jwtTokenUtil.generateUserNameStr(loginUser.getUsername());
        Map<String, String> tokenMap = new HashMap<>(4);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("tokenHeader", tokenHeader);

        return CommonResult.success(tokenMap);
    }
}

