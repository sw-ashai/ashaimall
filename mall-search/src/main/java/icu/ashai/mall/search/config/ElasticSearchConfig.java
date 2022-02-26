package icu.ashai.mall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ashai
 * @date 2/26/2022 11:08 PM
 * @Description
 */
@Configuration
public class ElasticSearchConfig {

    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader("Authorization", "Bearer" + TOKEN);
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory
//                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024)
//        );
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost("ashai.icu", 8200, "http")
        ));
    }

}
