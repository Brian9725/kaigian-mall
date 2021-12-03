package pers.brian.mall.modules.ums.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.common.constant.ComConstants;
import pers.brian.mall.modules.ums.model.dto.UmsAdminLoginParam;
import pers.brian.mall.modules.ums.model.dto.UmsAdminParam;
import pers.brian.mall.modules.ums.model.dto.UpdateAdminPasswordParam;
import pers.brian.mall.modules.ums.model.entity.UmsAdmin;
import pers.brian.mall.modules.ums.model.entity.UmsRole;
import pers.brian.mall.modules.ums.service.UmsAdminService;
import pers.brian.mall.modules.ums.service.UmsRoleService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 后台用户管理
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Controller
@Api(tags = "UmsAdminController")
@RequestMapping("/admin")
public class UmsAdminController {

    private final HttpSession session;

    private final UmsAdminService adminService;

    private final UmsRoleService roleService;

    @Autowired
    public UmsAdminController(HttpSession session, UmsAdminService adminService, UmsRoleService roleService) {
        this.session = session;
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, String>> login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        UmsAdmin login = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (login == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        session.setAttribute(ComConstants.ADMIN_CURRENT_USER, login);
        System.out.println(session.getId());
        Map<String, String> tokenMap = new HashMap<>(16);
        // jwt
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getAdminInfo() {
        UmsAdmin umsAdmin = (UmsAdmin) session.getAttribute(ComConstants.ADMIN_CURRENT_USER);
        System.out.println(session.getId());
        Map<String, Object> data = new HashMap<>(16);
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> logout() {
        session.setAttribute(ComConstants.ADMIN_CURRENT_USER, null);
        return CommonResult.success(true);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UmsAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getItem(@PathVariable Long id) {
        UmsAdmin admin = adminService.getById(id);
        return CommonResult.success(admin);
    }

    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@PathVariable Long id, @RequestBody UmsAdmin admin) {
        boolean success = adminService.update(id, admin);
        if (success) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@PathVariable Long id) {
        boolean success = adminService.delete(id);
        if (success) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        boolean success = adminService.update(id, umsAdmin);
        if (success) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateRole(@RequestParam("adminId") Long adminId,
                                            @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }
}
