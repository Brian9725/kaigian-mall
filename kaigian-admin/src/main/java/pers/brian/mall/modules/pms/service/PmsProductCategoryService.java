package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.model.dto.PmsProductCategoryChildrenDTO;
import pers.brian.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductCategory;

import java.util.List;

/**
 * 产品分类 服务类
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    /**
     * 通过parentId查询商品分类
     *
     * @param parentId 父id，一级类别父id为0
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 查询到的商品分类列表
     */
    Page<PmsProductCategory> page(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 更新导航栏状态
     *
     * @param navStatus 将要更新的导航栏状态值
     * @param ids       待更新的商品分类id列表
     * @return 更新是否成功
     */
    boolean updateNavStatus(Integer navStatus, List<Long> ids);

    /**
     * 更新商品分类是否显示
     *
     * @param showStatus showStatus
     * @param ids        待更新的商品分类id列表
     * @return 更新是否成功
     */
    boolean updateShowStatus(Integer showStatus, List<Long> ids);

    /**
     * 查询商品分类及其子分类的列表
     *
     * @return 商品分类及其子分类的列表
     */
    List<PmsProductCategoryChildrenDTO> listWithChildren();

    /**
     * 自定义保存商品分类
     *
     * @param productCategoryDTO 待保存的商品分类信息
     * @return 保存是否成功
     */
    boolean customSave(PmsProductCategoryDTO productCategoryDTO);

    /**
     * 更新商品分类信息
     *
     * @param productCategoryDTO 待更新的商品分类信息
     * @return 更新是否成功
     */
    boolean update(PmsProductCategoryDTO productCategoryDTO);
}
