package com.madshines;

import com.madshines.pojo.Content;
import com.madshines.repository.ContentRepository;
import com.madshines.service.ContentService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootesJindongApplicationTests {
    @Autowired
    ContentService contentService;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Autowired
    ContentRepository contentRepository;
    @Test
    void contextLoads() throws IOException {
        contentService.parseContent("java");
    }
    @Test
    void search() throws IOException {
        List<Map<String, Object>> list = contentService.searchPage("疯狂", 1, 10);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }
    @Test
    void searchJD() throws IOException {
        SearchRequest searchRequest=new SearchRequest("jd");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("title","零基础"));
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(3));
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }
    @Test
    void insertJD(){
        contentRepository.save(new Content("java","picture","10.3"));
    }
}
