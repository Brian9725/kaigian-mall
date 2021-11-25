package pers.brian.mall.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.dto.OssPolicyResult;
import pers.brian.mall.service.OssService;

/**
 * @Description: OSS资源管理
 * @Author: BrianHu
 * @Create: 2021-11-17 16:56
 * @Version: 0.0.1
 **/
@RestController
@RequestMapping(value = "/aliyun/oss")
public class OssController {

    private final OssService ossService;

    @Autowired
    public OssController(OssService ossService) {
        this.ossService = ossService;
    }

    @ApiOperation("请求OSS授权")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult resultMap = ossService.policy();
        if (resultMap != null) {
            return CommonResult.success(resultMap);
        } else {
            return CommonResult.failed();
        }
    }
}
