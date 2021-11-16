package pers.brian.mall.modules.pms.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.model.PmsProduct;
import pers.brian.mall.modules.pms.service.PmsProductService;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/pms/pmsProduct")
public class PmsProductController {

	private final PmsProductService productService;

	@Autowired
	public PmsProductController(PmsProductService productService) {
		this.productService = productService;
	}

	@ApiOperation("查询商品")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<List<PmsProduct>> list() {
		List<PmsProduct> products = productService.list();
		return CommonResult.success(products);
	}
}

