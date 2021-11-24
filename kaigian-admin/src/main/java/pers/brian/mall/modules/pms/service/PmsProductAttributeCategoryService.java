package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.dto.ProductAttributeCateDTO;
import pers.brian.mall.modules.pms.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
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
	List<ProductAttributeCateDTO> getListWithAttr();

	/**
	 * 自定义添加商品类型
	 *
	 * @param pmsProductAttributeCategory 待添加的商品类型信息
	 * @return 是否添加成功
	 */
	boolean customSave(PmsProductAttributeCategory pmsProductAttributeCategory);
}
