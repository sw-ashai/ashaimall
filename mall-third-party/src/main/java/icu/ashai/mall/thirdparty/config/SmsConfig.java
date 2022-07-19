package icu.ashai.mall.thirdparty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ashai
 * @date 2022/7/19 22:18
 * @email ashai@gmail.com
 * @description
 */
@Data
@Component
@ConfigurationProperties("thirdparty.sms")
public class SmsConfig {
    private String host;
    private String path;
    private String method;
    private String appcode;
    private String templateId;
}
