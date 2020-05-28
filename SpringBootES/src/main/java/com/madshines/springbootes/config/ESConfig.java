package com.madshines.springbootes.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:madshines
 * Date:2020/5/28
 * Package:com.madshines.springbootes.config
 * Description:es配置
 **/
@Configuration
public class ESConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("47.92.225.99", 9200, "http")
        ));
        return restHighLevelClient;
    }
}
