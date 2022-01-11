package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.model.entity.PmsBrand;

import java.util.List;

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

    /**
     * 更新品牌是否显示状态
     *
     * @param showStatus 显示状态
     * @param ids        需要更改的id列表
     * @return 是否更新成功
     */
    Boolean updateShowStatus(Integer showStatus, List<Long> ids);

    /**
     * 更新品牌是否是品牌制造商
     *
     * @param factoryStatus 是否品牌制造商
     * @param ids           需要更改的id列表
     * @return 是否更新成功
     */
    Boolean updateFactoryStatus(Integer factoryStatus, List<Long> ids);
}
