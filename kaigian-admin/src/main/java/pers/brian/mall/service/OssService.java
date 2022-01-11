package pers.brian.mall.service;

import pers.brian.mall.dto.OssPolicyResult;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-17 16:58
 * @Version: 0.0.1
 **/
public interface OssService {
    /**
     * 获取OSS授权信息
     *
     * @return OSS授权信息
     */
    OssPolicyResult policy();
}
