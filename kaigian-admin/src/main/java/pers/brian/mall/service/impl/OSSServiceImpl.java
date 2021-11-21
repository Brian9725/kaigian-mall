package pers.brian.mall.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pers.brian.mall.common.util.StringConstants;
import pers.brian.mall.dto.OSSPolicyResult;
import pers.brian.mall.service.OSSService;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-17 16:58
 * @Version: 0.0.1
 **/
@Service
@Slf4j
public class OSSServiceImpl implements OSSService {

    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;
    @Value("${aliyun.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;

    private final OSS ossClient;

    @Autowired
    public OSSServiceImpl(OSS ossClient) {
        this.ossClient = ossClient;
    }

    @Override
    public OSSPolicyResult policy() {
        OSSPolicyResult result = OSSPolicyResult.builder().build();
        // 存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dir = ALIYUN_OSS_DIR_PREFIX + sdf.format(new Date());
        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000L;
        Date expiration = new Date(expireEndTime);
        // 文件大小
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024L * 1024L;

        // 提交节点
        String action = StringConstants.HTTP_PREFIX + ALIYUN_OSS_BUCKET_NAME + StringConstants.DOT + ALIYUN_OSS_ENDPOINT;
        try {
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            // 返回结果
            result.setAccessKeyId(((OSSClient) ossClient).getCredentialsProvider().getCredentials().getAccessKeyId())
                    .setPolicy(policy)
                    .setSignature(signature)
                    .setDir(dir)
                    .setHost(action);
        } catch (Exception e) {
            log.error("签名生成失败", e);
        }
        return result;
    }
}
