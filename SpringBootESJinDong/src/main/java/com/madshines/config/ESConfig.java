package com.madshines.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :madshines
 * @Date: 2020-06-09
 * @Description: com.madshines.config
 * @version: 1.0
 */
@Configuration
public class ESConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("madshines.cn",9200,"http")));
    }
}
