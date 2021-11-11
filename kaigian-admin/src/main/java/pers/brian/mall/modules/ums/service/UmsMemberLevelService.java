package pers.brian.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.ums.model.UmsMemberLevel;

import java.util.List;

/**
 * @Description:
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @Author: BrianHu
 * @Date: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
public interface UmsMemberLevelService extends IService<UmsMemberLevel> {

    List<UmsMemberLevel> list(Integer defaultStatus);
}
