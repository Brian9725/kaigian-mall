package pers.brian.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.ums.mapper.UmsMemberLevelMapper;
import pers.brian.mall.modules.ums.model.UmsMemberLevel;
import pers.brian.mall.modules.ums.service.UmsMemberLevelService;

import java.util.List;

/**
 * @Description: <p>
 * 会员等级表 服务实现类
 * </p>
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Service
public class UmsMemberLevelServiceImpl extends ServiceImpl<UmsMemberLevelMapper, UmsMemberLevel> implements UmsMemberLevelService {

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        QueryWrapper<UmsMemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsMemberLevel::getDefaultStatus, defaultStatus);
        return this.list(queryWrapper);
    }
}
