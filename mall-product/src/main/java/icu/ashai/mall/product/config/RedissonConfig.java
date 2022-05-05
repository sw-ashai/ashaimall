package icu.ashai.mall.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ashai
 * @Date 2022/4/27 20:48
 * @Email ashai.cn@gmail.com
 * @Description
 */
@Configuration
public class RedissonConfig {

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redisson() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://106.14.198.234:2203").setPassword("19980612.ss");
		return Redisson.create(config);
	}
}
