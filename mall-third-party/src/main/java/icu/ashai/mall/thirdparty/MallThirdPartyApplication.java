package icu.ashai.mall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @Description 第三方服务启动类
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 下午 11:05 2021/12/2
*/
@SpringBootApplication
@EnableDiscoveryClient
public class MallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallThirdPartyApplication.class, args);
    }

}
