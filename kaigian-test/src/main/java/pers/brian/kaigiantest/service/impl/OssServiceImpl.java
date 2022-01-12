package pers.brian.kaigiantest.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.stereotype.Service;
import pers.brian.kaigiantest.service.OssService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author BrianHu
 * @create 2021-11-17 13:58
 **/
@Service
public class OssServiceImpl implements OssService {

	@Override
	public Map<String, String> policy() {
		// 请填写您的AccessKeyId。
		String accessId = "LTAI5tHFcVtYQo2oS9ngDJ1K";
		// 请填写您的AccessKeySecret。
		String accessKey = "jvaN4ORALLzX9JoQY0dt6v6pElInvd";
		// 请填写您的 endpoint。
		String endpoint = "oss-cn-beijing.aliyuncs.com";
		// 请填写您的 bucketname 。
		String bucket = "kaigian-mall";
		// host的格式为 bucketname.endpoint
		String host = "https://" + bucket + "." + endpoint;
		// callbackUrl为上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
		// String callbackUrl = "http://88.88.88.88:8888";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = simpleDateFormat.format(new Date());
		// 用户上传文件时指定的前缀。
		String dir = "test/" + format + "/";

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
		try {
			long expireTime = 30L;
			long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
			Date expiration = new Date(expireEndTime);
			// PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

			String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			String postSignature = ossClient.calculatePostSignature(postPolicy);

			Map<String, String> respMap = new LinkedHashMap<String, String>();
			respMap.put("accessid", accessId);
			respMap.put("policy", encodedPolicy);
			respMap.put("signature", postSignature);
			respMap.put("dir", dir);
			respMap.put("host", host);
			respMap.put("expire", String.valueOf(expireEndTime / 1000));
			return respMap;
		} catch (Exception e) {
			// Assert.fail(e.getMessage());
			System.out.println(e.getMessage());
		} finally {
			ossClient.shutdown();
		}
		return null;
	}
}
