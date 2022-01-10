package pers.brian.mall.config;

import pers.brian.mall.common.config.BaseSwaggerConfig;
import pers.brian.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * API文档相关配置
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("pers.brian.mall.modules")
                .title("KaiGian商城项目后台管理系统")
                .description("kaigian_mall项目后台管理接口文档")
                .contactName("brian")
                .version("0.0.1")
                .enableSecurity(false)
                .build();
    }
}
