package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.pms.mapper.PmsProductFullReductionMapper;
import pers.brian.mall.modules.pms.model.entity.PmsProductFullReduction;
import pers.brian.mall.modules.pms.service.PmsProductFullReductionService;

/**
 * 产品满减表(只针对同商品) 服务实现类
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
@Service
public class PmsProductFullReductionServiceImpl extends ServiceImpl<PmsProductFullReductionMapper, PmsProductFullReduction> implements PmsProductFullReductionService {

}
