package pers.brian.kaigiantest.service;

import java.util.Map;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-17 13:58
 * @Version: 0.0.1
 **/
public interface OSSService {

	/**
	 * 请求oss签名
	 * @return 返回oss签名信息
	 */
	Map<String,String> policy();
}
