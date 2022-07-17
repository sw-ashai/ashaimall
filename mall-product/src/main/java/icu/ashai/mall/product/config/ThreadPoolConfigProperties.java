package icu.ashai.mall.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ashai
 * @date 2022/7/17 21:49
 * @email ashai@gmail.com
 * @description
 */
@ConfigurationProperties("ashaimall.thread")
@Data
public class ThreadPoolConfigProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
