package pers.brian.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.ums.model.UmsResourceCategory;

import java.util.List;

/**
 * @Description: 后台资源分类管理Service
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
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
