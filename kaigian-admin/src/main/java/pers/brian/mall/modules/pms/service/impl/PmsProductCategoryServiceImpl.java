package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import pers.brian.mall.modules.pms.dto.PmsProductCategoryParam;
import pers.brian.mall.modules.pms.model.PmsProductCategory;
import pers.brian.mall.modules.pms.mapper.PmsProductCategoryMapper;
import pers.brian.mall.modules.pms.model.PmsProductCategoryWithChildrenItem;
import pers.brian.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

	private final PmsProductCategoryMapper productCategoryMapper;

	@Autowired
	public PmsProductCategoryServiceImpl(PmsProductCategoryMapper productCategoryMapper) {
		this.productCategoryMapper = productCategoryMapper;
	}

	@Override
	public Page<PmsProductCategory> page(Long parentId, Integer pageNum, Integer pageSize) {
		Page<PmsProductCategory> page = new Page<>(pageNum, pageSize);
		QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(PmsProductCategory::getParentId, parentId);
		return this.page(page, queryWrapper);
	}

	@Override
	public int delete(Long id) {
		return productCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int create(PmsProductCategoryParam productCategoryParam) {
		return 0;
	}

	@Override
	public int update(Long id, PmsProductCategoryParam productCategoryParam) {
		return 0;
	}

	@Override
	public int updateNavStatus(List<Long> ids, Integer navStatus) {
		return 0;
	}

	@Override
	public int updateShowStatus(List<Long> ids, Integer showStatus) {
		return 0;
	}

	@Override
	public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
		return null;
	}
}
