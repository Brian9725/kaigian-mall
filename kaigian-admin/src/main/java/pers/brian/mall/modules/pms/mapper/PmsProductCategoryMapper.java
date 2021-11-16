package pers.brian.mall.modules.pms.mapper;

import org.springframework.stereotype.Component;
import pers.brian.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import javax.annotation.Resource;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Component
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

	/**
	 * 根据id删除商品分类
	 * @param id 商品分类id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);
}
