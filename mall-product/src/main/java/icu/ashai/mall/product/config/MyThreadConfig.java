package icu.ashai.mall.product.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ashai
 * @date 2022/7/17 21:47
 * @email ashai@gmail.com
 * @description
 */
@Configuration
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
public class MyThreadConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties poolConfig){
        return new ThreadPoolExecutor(poolConfig.getCoreSize(), poolConfig.getMaxSize(), poolConfig.getKeepAliveTime(),
                                      TimeUnit.SECONDS,
                                      new LinkedBlockingDeque<>(100000),
                                      Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    }
}
