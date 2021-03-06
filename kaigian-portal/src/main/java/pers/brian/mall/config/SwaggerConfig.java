package pers.brian.mall.config;

import org.springframework.context.annotation.Configuration;
import pers.brian.mall.common.config.BaseSwaggerConfig;
import pers.brian.mall.common.domain.SwaggerProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: API文档相关配置
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("pers.brian.mall.modules")
                .title("KaiGian商城项目前台管理系统")
                .description("kaigian_mall项目前台管理接口文档")
                .contactName("brian")
                .version("0.0.1")
                .enableSecurity(false)
                .build();
    }
}
