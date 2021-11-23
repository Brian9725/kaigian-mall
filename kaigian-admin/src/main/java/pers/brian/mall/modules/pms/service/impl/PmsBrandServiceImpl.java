package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.brian.mall.modules.pms.mapper.PmsBrandMapper;
import pers.brian.mall.modules.pms.model.PmsBrand;
import pers.brian.mall.modules.pms.service.PmsBrandService;

import java.util.List;

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

    @Override
    public Boolean updateShowStatus(Integer showStatus, List<Long> ids) {
        UpdateWrapper<PmsBrand> brandUpdateWrapper = new UpdateWrapper<>();
        brandUpdateWrapper.lambda()
                .set(PmsBrand::getShowStatus, showStatus)
                .in(PmsBrand::getId, ids);
        return this.update(brandUpdateWrapper);
    }

    @Override
    public Boolean updateFactoryStatus(Integer factoryStatus, List<Long> ids) {
        UpdateWrapper<PmsBrand> brandUpdateWrapper = new UpdateWrapper<>();
        brandUpdateWrapper.lambda()
                .set(PmsBrand::getFactoryStatus, factoryStatus)
                .in(PmsBrand::getId, ids);
        return this.update(brandUpdateWrapper);
    }
}
