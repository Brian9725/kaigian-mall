package pers.brian.mall.modules.ums.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.ums.mapper.UmsResourceMapper;
import pers.brian.mall.modules.ums.model.UmsResource;
import pers.brian.mall.modules.ums.service.UmsAdminCacheService;
import pers.brian.mall.modules.ums.service.UmsResourceService;

import java.util.Date;

/**
 * @Description: 后台资源管理Service实现类
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements UmsResourceService {

    private final UmsAdminCacheService adminCacheService;

    @Autowired
    public UmsResourceServiceImpl(UmsAdminCacheService adminCacheService) {
        this.adminCacheService = adminCacheService;
    }


    @Override
    public boolean create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        return save(umsResource);
    }

    @Override
    public boolean update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        boolean success = updateById(umsResource);
        adminCacheService.delResourceListByResource(id);
        return success;
    }

    @Override
    public boolean delete(Long id) {
        boolean success = removeById(id);
        adminCacheService.delResourceListByResource(id);
        return success;
    }

    @Override
    public Page<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        Page<UmsResource> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UmsResource> lambda = wrapper.lambda();
        if (categoryId != null) {
            lambda.eq(UmsResource::getCategoryId, categoryId);
        }
        if (StrUtil.isNotEmpty(nameKeyword)) {
            lambda.like(UmsResource::getName, nameKeyword);
        }
        if (StrUtil.isNotEmpty(urlKeyword)) {
            lambda.like(UmsResource::getUrl, urlKeyword);
        }
        return page(page, wrapper);
    }
}
