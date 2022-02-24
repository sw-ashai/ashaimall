package icu.ashai.mall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description 商城产品服务
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 下午 04:02 2021/11/21
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "icu.ashai.mall.product.feign")
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}
