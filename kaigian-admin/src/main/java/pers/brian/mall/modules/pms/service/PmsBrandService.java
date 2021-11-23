package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.brian.mall.modules.pms.model.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
public interface PmsBrandService extends IService<PmsBrand> {

    /**
     * 获取品牌管理列表
     *
     * @param keyword  关键词
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 品牌管理列表
     */
    Page<PmsBrand> page(String keyword, Integer pageNum, Integer pageSize);
}
