package pers.brian.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import pers.brian.mall.modules.pms.model.dto.ProductUpdateInitDTO;
import pers.brian.mall.modules.pms.model.entity.PmsProduct;

/**
 * 商品信息 Mapper 接口
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
@Component
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    /**
     * 获取编辑状态下商品信息
     *
     * @param id 商品id
     * @return 对应的商品信息
     */
    ProductUpdateInitDTO getUpdateInfo(Long id);
}
