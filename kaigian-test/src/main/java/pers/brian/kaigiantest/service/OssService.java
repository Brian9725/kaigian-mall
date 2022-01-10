package pers.brian.kaigiantest.service;

import java.util.Map;

/**
 * @author BrianHu
 * @create 2021-11-17 13:58
 **/
public interface OssService {

    /**
     * 请求oss签名
     *
     * @return 返回oss签名信息
     */
    Map<String, String> policy();
}
