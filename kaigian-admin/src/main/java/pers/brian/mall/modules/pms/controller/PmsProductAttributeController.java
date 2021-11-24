package pers.brian.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonPage;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.pms.dto.RelationAttrInfoDTO;
import pers.brian.mall.modules.pms.model.PmsProductAttribute;
import pers.brian.mall.modules.pms.service.PmsProductAttributeService;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    private final PmsProductAttributeService productAttributeService;

    @Autowired
    public PmsProductAttributeController(PmsProductAttributeService productAttributeService) {
        this.productAttributeService = productAttributeService;
    }

    @RequestMapping(value = "/list/{cId}")
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cId,
                                                                 @RequestParam(value = "type", defaultValue = "0") Integer type,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<PmsProductAttribute> productAttributePage = productAttributeService.list(cId, type, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(productAttributePage));
    }

    @RequestMapping(value = "/attrInfo/{cId}")
    @ResponseBody
    public CommonResult<List<RelationAttrInfoDTO>> getRelationAttrInfoByCid(@PathVariable Long cId) {
        List<RelationAttrInfoDTO> list = productAttributeService.getRelationAttrInfoByCid(cId);
        return CommonResult.success(list);
    }
}

