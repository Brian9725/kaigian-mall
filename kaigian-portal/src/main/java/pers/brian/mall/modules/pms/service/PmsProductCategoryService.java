package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.dto.HomeMenusDTO;
import pers.brian.mall.modules.pms.model.PmsProductCategory;

import java.util.List;

/**
 * 产品分类 服务类
 *
 * @author BrianHu
 * @create 2021-11-30 12:00
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    /**
     * 获取首页类型导航菜单
     *
     * @return 首页类型导航菜单信息
     */
    List<HomeMenusDTO> getMenus();
}
