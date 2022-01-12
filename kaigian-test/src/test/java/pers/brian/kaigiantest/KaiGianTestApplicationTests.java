package pers.brian.kaigiantest;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.brian.kaigiantest.service.OssService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@SpringBootTest
class KaiGianTestApplicationTests {

	@Autowired
	private OssService ossService;

	@Test
	void uploadOSS() throws FileNotFoundException {
		// yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
		String endpoint = "https://oss-cn-beijing.aliyuncs.com";
		// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
		String accessKeyId = "LTAI5tHFcVtYQo2oS9ngDJ1K";
		String accessKeySecret = "jvaN4ORALLzX9JoQY0dt6v6pElInvd";

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// 上传文件流。
		InputStream inputStream = new FileInputStream("C:\\Users\\Brian\\Desktop\\163.png");
		// 注释下面的语句以防止maven打包时报错
		// ossClient.putObject("kaigian-mall", "164.jpg", inputStream);

		// 关闭OSSClient。
		ossClient.shutdown();

		System.out.println("上传成功！");
	}

}
