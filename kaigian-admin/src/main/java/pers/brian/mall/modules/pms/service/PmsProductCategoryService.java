package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.dto.ProductCategoryDTO;
import pers.brian.mall.modules.pms.dto.ProductCategoryParam;
import pers.brian.mall.modules.pms.dto.ProductCateChildrenDTO;
import pers.brian.mall.modules.pms.model.PmsProductCategory;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
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
     * 创建新的商品分类
     *
     * @param productCategoryParam 新的商品分类信息
     * @return 新建的商品分类数
     */
    int create(ProductCategoryParam productCategoryParam);

    /**
     * 根据更新商品分类信息
     *
     * @param id                   商品分类id
     * @param productCategoryParam 新的商品分类信息
     * @return 更新的纪录条数
     */
    int update(Long id, ProductCategoryParam productCategoryParam);

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
    List<ProductCateChildrenDTO> listWithChildren();

    /**
     * 自定义保存商品分类
     *
     * @param productCategoryDTO 待保存的商品分类信息
     * @return 保存是否成功
     */
    boolean customSave(ProductCategoryDTO productCategoryDTO);

    /**
     * 更新商品分类信息
     *
     * @param productCategoryDTO 待更新的商品分类信息
     * @return 更新是否成功
     */
    boolean update(ProductCategoryDTO productCategoryDTO);
}
