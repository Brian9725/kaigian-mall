package pers.brian.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.dto.PmsProductCategoryParam;
import pers.brian.mall.modules.pms.model.PmsProductCategory;
import pers.brian.mall.modules.pms.model.PmsProductCategoryWithChildrenItem;
import pers.brian.mall.modules.pms.service.PmsProductCategoryService;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

	private final PmsProductCategoryService productCategoryService;

	@Autowired
	public PmsProductCategoryController(PmsProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}

	@ApiOperation("商品分类列表")
	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable(value = "parentId") Long parentId,
																@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
																@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		Page<PmsProductCategory> productCategoryPage = productCategoryService.page(parentId, pageNum, pageSize);
		return CommonResult.success(CommonPage.restPage(productCategoryPage));
	}

	@ApiOperation("删除商品分类")
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public CommonResult delete(@PathVariable(value = "id") Long id) {
		int count = productCategoryService.delete(id);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("获取商品分类详情")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CommonResult<PmsProductCategory> getInfo(@PathVariable(value = "id") Long id) {
		PmsProductCategory productCategory = productCategoryService.getById(id);
		return CommonResult.success(productCategory);
	}


	@ApiOperation("添加产品分类")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
		int count = productCategoryService.create(productCategoryParam);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("修改商品分类")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(@PathVariable Long id,
							   @Validated
							   @RequestBody PmsProductCategoryParam productCategoryParam) {
		int count = productCategoryService.update(id, productCategoryParam);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("修改导航栏显示状态")
	@RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
		int count = productCategoryService.updateNavStatus(ids, navStatus);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("修改显示状态")
	@RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
		int count = productCategoryService.updateShowStatus(ids, showStatus);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("查询所有一级分类及子分类")
	@RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
		List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
		return CommonResult.success(list);
	}
}

