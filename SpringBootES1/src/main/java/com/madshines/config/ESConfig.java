package com.madshines.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:madshines
 * Date:2020/6/7
 * Package:com.madshines.config
 * Description:
 **/
@Configuration
public class ESConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient restHighLevelClient=new RestHighLevelClient(RestClient.builder(
                new HttpHost("madshines.cn",9200,"http")));
        return restHighLevelClient;
    }
}
