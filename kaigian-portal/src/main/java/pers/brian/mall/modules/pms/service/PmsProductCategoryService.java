package pers.brian.mall.modules.pms.service;

import pers.brian.mall.dto.HomeMenusDTO;
import pers.brian.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-30
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    /**
     * 获取首页类型导航菜单
     *
     * @return 首页类型导航菜单信息
     */
    List<HomeMenusDTO> getMenus();
}
