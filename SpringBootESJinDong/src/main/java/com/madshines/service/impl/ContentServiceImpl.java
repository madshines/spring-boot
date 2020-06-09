package com.madshines.service.impl;

import com.alibaba.fastjson.JSON;
import com.madshines.pojo.Content;
import com.madshines.repository.ContentRepository;
import com.madshines.service.ContentService;
import com.madshines.utils.HtmlParestUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author :madshines
 * @Date: 2020-06-09
 * @Description: com.madshines.service.impl
 * @version: 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    @Qualifier(value = "restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    @Qualifier(value = "htmlParestUtil")
    private HtmlParestUtil htmlParestUtil;
    @Autowired
    ContentRepository contentRepository;

    @Override
    public void saveContent(Content content) {
        //将查询结果持久化
        contentRepository.save(content);
    }

    @Override
    public boolean parseContent(String keyWord) throws IOException {
        List<Content> contents = htmlParestUtil.parse(keyWord);
        //将查询的结果放入到es中
        BulkRequest bulkRequest =new BulkRequest();
        for (Content content : contents) {
            saveContent(content);
            IndexRequest indexRequest = new IndexRequest("jd");
            String s = JSON.toJSONString(content);
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        RestStatus status = bulk.status();
        System.out.println(status);
        return !bulk.hasFailures();
    }

    @Override
    public List<Map<String, Object>> searchPage(String keyWord, int pageNo, int pageSize) throws IOException {
        if (pageNo<=1){
            pageNo=1;
        }
        //条件搜索
        SearchRequest searchRequest =new SearchRequest("jd");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("title",keyWord));
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(3));
        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        List<Map<String,Object>> list=new ArrayList<>();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            list.add(sourceAsMap);
        }
        return list;
    }
}
