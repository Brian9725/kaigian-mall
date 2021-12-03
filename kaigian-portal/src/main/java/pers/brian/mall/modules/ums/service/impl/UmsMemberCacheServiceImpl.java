package pers.brian.mall.modules.ums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pers.brian.mall.common.service.RedisService;
import pers.brian.mall.modules.ums.model.UmsMember;
import pers.brian.mall.modules.ums.service.UmsMemberCacheService;
import pers.brian.mall.modules.ums.service.UmsMemberService;

/**
 * @Description: 前台用户缓存管理Service实现类
 * @Author: BrianHu
 * @Create: 2021-12-03 12:08
 * @Version: 0.0.1
 **/
@Service
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {
    @Autowired
    private UmsMemberService memberService;

    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;

    @Override
    public void delMember(Long adminId) {
        UmsMember member = memberService.getById(adminId);
        if (member != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + member.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public UmsMember getUser(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + username;
        return (UmsMember) redisService.get(key);
    }

    @Override
    public void setUser(UmsMember admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }


}
