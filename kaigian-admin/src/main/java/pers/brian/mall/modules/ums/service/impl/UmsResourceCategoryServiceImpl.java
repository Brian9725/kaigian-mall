package pers.brian.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.brian.mall.modules.ums.mapper.UmsResourceCategoryMapper;
import pers.brian.mall.modules.ums.model.UmsResourceCategory;
import pers.brian.mall.modules.ums.service.UmsResourceCategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 后台资源分类管理Service实现类
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Service
public class UmsResourceCategoryServiceImpl extends ServiceImpl<UmsResourceCategoryMapper, UmsResourceCategory> implements UmsResourceCategoryService {

    @Override
    public List<UmsResourceCategory> listAll() {
        QueryWrapper<UmsResourceCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByAsc(UmsResourceCategory::getSort);
        return list(wrapper);
    }

    @Override
    public boolean create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return save(umsResourceCategory);
    }
}
