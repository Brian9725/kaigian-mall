package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.dto.PmsProductCategoryParam;
import pers.brian.mall.modules.pms.model.PmsProductCategory;
import pers.brian.mall.modules.pms.model.PmsProductCategoryWithChildrenItem;

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
	 * @param parentId 父id，一级类别父id为0
	 * @param pageNum 页数
	 * @param pageSize 页大小
	 * @return 查询到的商品分类列表
	 */
	Page<PmsProductCategory> page(Long parentId, Integer pageNum, Integer pageSize);

	/**
	 * 创建新的商品分类
	 * @param productCategoryParam 新的商品分类信息
	 * @return 新建的商品分类数
	 */
	int create(PmsProductCategoryParam productCategoryParam);

	/**
	 * 根据更新商品分类信息
	 * @param id 商品分类id
	 * @param productCategoryParam 新的商品分类信息
	 * @return 更新的纪录条数
	 */
	int update(Long id, PmsProductCategoryParam productCategoryParam);

	/**
	 * 更新导航栏状态
	 * @param ids 待更新的商品分类id列表
	 * @param navStatus 将要更新的导航栏状态值
	 * @return 更新是否成功
	 */
	boolean updateNavStatus(List<Long> ids, Integer navStatus);

	/**
	 * 更新商品分类是否显示
	 * @param ids 待更新的商品分类id列表
	 * @param showStatus showStatus
	 * @return 更新是否成功
	 */
	boolean updateShowStatus(List<Long> ids, Integer showStatus);

	/**
	 * 查询商品分类及其子分类的列表
	 * @return 商品分类及其子分类的列表
	 */
	List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
