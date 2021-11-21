package pers.brian.mall.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.dto.OSSPolicyResult;
import pers.brian.mall.service.OSSService;

/**
 * @Description: OSS资源管理
 * @Author: BrianHu
 * @Create: 2021-11-17 16:56
 * @Version: 0.0.1
 **/
@RestController
@RequestMapping(value = "/aliyun/oss")
public class OSSController {

	private final OSSService ossService;

	@Autowired
	public OSSController(OSSService ossService) {
		this.ossService = ossService;
	}

	@ApiOperation("请求OSS授权")
	@RequestMapping(value = "/policy", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<OSSPolicyResult> policy() {
		OSSPolicyResult resultMap = ossService.policy();
		if (resultMap != null) {
			return CommonResult.success(resultMap);
		} else {
			return CommonResult.failed();
		}
	}
}
