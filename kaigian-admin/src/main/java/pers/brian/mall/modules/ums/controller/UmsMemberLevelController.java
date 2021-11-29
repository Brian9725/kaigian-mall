package pers.brian.mall.modules.ums.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.brian.mall.common.api.CommonResult;
import pers.brian.mall.modules.ums.model.UmsMemberLevel;
import pers.brian.mall.modules.ums.service.UmsMemberLevelService;

import java.util.List;

/**
 * @Description: <p>
 * 会员等级表 前端控制器
 * </p>
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    private final UmsMemberLevelService memberLevelService;

    @Autowired
    public UmsMemberLevelController(UmsMemberLevelService memberLevelService) {
        this.memberLevelService = memberLevelService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam(value = "defaultStatus", defaultValue = "0") Integer defaultStatus) {
        List<UmsMemberLevel> list = memberLevelService.list(defaultStatus);
        return CommonResult.success(list);
    }
}

