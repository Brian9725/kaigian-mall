package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.model.dto.PmsProductAttributeCategoryDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttributeCategory;

import java.util.List;

/**
 * 产品属性分类表 服务类
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    /**
     * 查询商品类型列表
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 商品类型列表
     */
    Page<PmsProductAttributeCategory> page(Integer pageNum, Integer pageSize);

    /**
     * 筛选属性下拉级联数据
     *
     * @return 筛选属性下拉级联数据
     */
    List<PmsProductAttributeCategoryDTO> getListWithAttr();

    /**
     * 自定义添加商品类型
     *
     * @param pmsProductAttributeCategoryParams 待添加的商品类型信息
     * @return 是否添加成功
     */
    boolean customSave(PmsProductAttributeCategory pmsProductAttributeCategoryParams);
}
