package icu.ashai.mall.thirdparty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ashai
 * @date 2022/7/19 22:20
 * @email ashai@gmail.com
 * @description
 */
@Data
@Component
@ConfigurationProperties("spring.cloud.alicloud")
public class AliCloudConfig {
    private String accessKey;
    private String secretKey;
    private OssConfig oss;

    @Data
    public static class OssConfig {
        private String endpoint;
        private String bucket;
    }
}
