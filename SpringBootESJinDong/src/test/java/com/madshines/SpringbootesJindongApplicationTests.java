package com.madshines;

import com.madshines.pojo.Content;
import com.madshines.pojo.Role;
import com.madshines.pojo.User;
import com.madshines.repository.ContentRepository;

import com.madshines.repository.RoleRepository;
import com.madshines.repository.UserRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class SpringbootesJindongApplicationTests {
    @Autowired
    ContentService contentService;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
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
        //contentRepository.save(new Content("java","picture","10.3"));
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String n = bCryptPasswordEncoder.encode("123456");
        System.out.println(n);

    }
    @Test
    void find(){
        User name = userRepository.findByUserName("tom");
        System.out.println(name);
//        Optional<User> id = loginRepository.findById(1);
//        System.out.println(id);
        List<Role> userName = roleRepository.findRoleByUserName("张宇恒");
        for (Role role : userName) {
            System.out.println(role);
        }
    }
}
