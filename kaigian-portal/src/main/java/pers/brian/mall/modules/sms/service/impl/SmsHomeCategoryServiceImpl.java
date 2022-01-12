package pers.brian.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.brian.mall.dto.HomeGoodsSaleDTO;
import pers.brian.mall.modules.sms.mapper.SmsHomeCategoryMapper;
import pers.brian.mall.modules.sms.model.SmsHomeCategory;
import pers.brian.mall.modules.sms.service.SmsHomeCategoryService;

import java.util.List;

/**
 * 服务实现类
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Service
public class SmsHomeCategoryServiceImpl extends ServiceImpl<SmsHomeCategoryMapper, SmsHomeCategory> implements SmsHomeCategoryService {

    @Autowired
    private SmsHomeCategoryMapper homeCategoryMapper;

    @Override
    public List<HomeGoodsSaleDTO> getGoodsSale() {
        return homeCategoryMapper.getHomeCategoryWithProduct();
    }
}
