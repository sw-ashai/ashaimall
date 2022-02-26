package icu.ashai.mall.search;


import icu.ashai.mall.search.config.ElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;


    @Test
    public void contextLoads() {
        System.out.println(client);
    }

    @Test
    void indexData(){
        IndexRequest users = new IndexRequest("users");
        users.id("1");
        users.source("userName","zhangsan","age","18","gender","男");

        try {
            IndexResponse index = client.index(users, ElasticSearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Data
    class User{
        private String userName;
        private String gender;
        private Integer age;
    }
}


