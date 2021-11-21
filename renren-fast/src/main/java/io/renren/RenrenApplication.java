/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description  商城后台管理服务
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 下午 05:06 2021/11/21
*/
@SpringBootApplication
@EnableDiscoveryClient
public class RenrenApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenrenApplication.class, args);
		System.out.println("====================================================================================================================\n" +
				"\n" +
				"                    ashaiMall管理服务启动成功\n" +
				"\n" +
				"====================================================================================================================");
	}

}