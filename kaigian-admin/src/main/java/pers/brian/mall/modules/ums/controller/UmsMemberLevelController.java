package pers.brian.mall.modules.ums.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.ums.model.entity.UmsMemberLevel;
import pers.brian.mall.modules.ums.service.UmsMemberLevelService;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    private UmsMemberLevelService memberLevelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam(value = "defaultStatus", defaultValue = "0") Integer defaultStatus) {
        List<UmsMemberLevel> list = memberLevelService.list(defaultStatus);
        return CommonResult.success(list);
    }
}

