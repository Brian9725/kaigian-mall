package pers.brian.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.ums.model.entity.UmsResourceCategory;

import java.util.List;

/**
 * 后台资源分类管理Service
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
public interface UmsResourceCategoryService extends IService<UmsResourceCategory> {

    /**
     * 获取所有资源分类
     */
    List<UmsResourceCategory> listAll();

    /**
     * 创建资源分类
     */
    boolean create(UmsResourceCategory umsResourceCategory);
}
