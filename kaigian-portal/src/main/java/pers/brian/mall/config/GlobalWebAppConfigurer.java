package pers.brian.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.brian.mall.common.util.JwtTokenUtil;
import pers.brian.mall.component.trade.alipay.model.TradePayProp;

/**
 * @Description: 权限验证拦截器
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@Configuration
public class GlobalWebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    private TradePayProp tradePayProp;

    /**
     * JWT验证工具类
     *
     * @return JWT工具类实例
     */
    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    /**
     * 将物理文件夹中的支付二维码映射为静态资源路径
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(tradePayProp.getHttpBasePath() + "/**").addResourceLocations("file:" + tradePayProp.getStorePath() + "/");
    }
}
