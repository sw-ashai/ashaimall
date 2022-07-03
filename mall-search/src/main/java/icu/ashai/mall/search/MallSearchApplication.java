package icu.ashai.mall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description 搜索服务
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 11:25 PM 2/26/2022
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MallSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSearchApplication.class, args);
    }

}
