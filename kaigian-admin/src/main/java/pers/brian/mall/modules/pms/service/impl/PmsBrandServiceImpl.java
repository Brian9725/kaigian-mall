package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import pers.brian.mall.modules.pms.model.PmsBrand;
import pers.brian.mall.modules.pms.mapper.PmsBrandMapper;
import pers.brian.mall.modules.pms.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-11-15
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Override
    public Page<PmsBrand> page(String keyword, Integer pageNum, Integer pageSize) {
        Page<PmsBrand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsBrand> brandQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            brandQueryWrapper.lambda().like(PmsBrand::getName, keyword);
        }
        brandQueryWrapper.lambda().orderByAsc(PmsBrand::getSort);
        return this.page(page, brandQueryWrapper);
    }
}
