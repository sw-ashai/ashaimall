package icu.ashai.mall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description 仓库服务
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 3:46 PM 2/24/2022
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "icu.ashai.mall.ware.feign")
public class MallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallWareApplication.class, args);
    }

}
