package pers.brian.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.brian.mall.modules.pms.mapper.PmsProductLadderMapper;
import pers.brian.mall.modules.pms.model.entity.PmsProductLadder;
import pers.brian.mall.modules.pms.service.PmsProductLadderService;

/**
 * 产品阶梯价格表(只针对同商品) 服务实现类
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
@Service
public class PmsProductLadderServiceImpl extends ServiceImpl<PmsProductLadderMapper, PmsProductLadder> implements PmsProductLadderService {

}
