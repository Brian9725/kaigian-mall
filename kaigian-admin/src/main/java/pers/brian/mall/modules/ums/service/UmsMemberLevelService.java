package pers.brian.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.brian.mall.modules.ums.model.entity.UmsMemberLevel;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
public interface UmsMemberLevelService extends IService<UmsMemberLevel> {

    /**
     * 获取会员列表
     *
     * @param defaultStatus 默认状态
     * @return 符合状态的会员列表
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
