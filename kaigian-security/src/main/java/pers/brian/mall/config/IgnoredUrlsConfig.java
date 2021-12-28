package pers.brian.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Description: 白名单url
 * @Author: BrianHu
 * @Create: 2021-12-27 10:27
 * @Version: 0.0.1
 **/
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoredUrlsConfig {
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
