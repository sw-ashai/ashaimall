package icu.ashai.mall.product.coonfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ashai
 * @date 2021/12/26 下午 02:51
 * @Description
 */
@Configuration
@EnableTransactionManagement
@MapperScan("icu.ashai.mall.product.dao")
public class MyBatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setOverflow(true);
        paginationInterceptor.setLimit(1000);

        return paginationInterceptor;
    }

}
