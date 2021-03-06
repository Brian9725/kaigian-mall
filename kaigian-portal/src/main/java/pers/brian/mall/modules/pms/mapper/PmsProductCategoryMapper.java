package pers.brian.mall.modules.pms.mapper;

import org.springframework.stereotype.Component;
import pers.brian.mall.dto.HomeMenusDTO;
import pers.brian.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-30
 */
@Component
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    /**
     * 获取首页类型导航菜单
     *
     * @return 首页类型导航菜单信息
     */
    List<HomeMenusDTO> getProductWithCategory();
}
