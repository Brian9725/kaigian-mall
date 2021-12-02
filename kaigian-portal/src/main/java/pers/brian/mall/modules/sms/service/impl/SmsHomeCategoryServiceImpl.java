package pers.brian.mall.modules.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pers.brian.mall.dto.HomeGoodsSaleDTO;
import pers.brian.mall.modules.sms.model.SmsHomeCategory;
import pers.brian.mall.modules.sms.mapper.SmsHomeCategoryMapper;
import pers.brian.mall.modules.sms.service.SmsHomeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Service
public class SmsHomeCategoryServiceImpl extends ServiceImpl<SmsHomeCategoryMapper, SmsHomeCategory> implements SmsHomeCategoryService {

    @Autowired
    SmsHomeCategoryMapper homeCategoryMapper;

    @Override
    public List<HomeGoodsSaleDTO> getGoodsSale() {
        return homeCategoryMapper.getHomeCategoryWithProduct();
    }
}
