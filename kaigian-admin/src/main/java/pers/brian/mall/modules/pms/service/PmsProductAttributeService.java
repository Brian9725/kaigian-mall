package pers.brian.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.pms.model.dto.PmsRelationAttrInfoDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProductAttribute;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {

    /**
     * 根据商品分类id获取关联的筛选属性
     *
     * @param cId 商品分类id
     * @return 关联的筛选属性
     */
    List<PmsRelationAttrInfoDTO> getRelationAttrInfoByCid(Long cId);

    /**
     * 根据id和type筛选商品类型属性
     *
     * @param cId      商品类型id
     * @param type     属性类型
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 筛选出的商品类型属性
     */
    Page<PmsProductAttribute> list(Long cId, Integer type, Integer pageNum, Integer pageSize);

    /**
     * 创建新的商品类型属性
     *
     * @param productAttribute 带添加的商品类型属性信息
     * @return 是否创建成功
     */
    boolean create(PmsProductAttribute productAttribute);

    /**
     * 删除id列表中的商品类型属性
     *
     * @param ids id列表
     * @return 是否删除成功
     */
    boolean delete(List<Long> ids);
}
