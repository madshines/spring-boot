package com.madshines.springbootes;

import com.alibaba.fastjson.JSON;
import com.madshines.springbootes.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

/*
* highLevelClient测试API*/
@SpringBootTest
class SpringbootesApplicationTests {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;
    @Test
    void contextLoads() {


    }
    //创建索引 PUT /技嘉/_doc
    @Test
    void testCreateIndex() throws IOException {
        //创建索引请求
        CreateIndexRequest indexRequest = new CreateIndexRequest("技嘉");
        //执行请求,获得相应
        IndicesClient indices = client.indices();
        CreateIndexResponse indexResponse = indices.create(indexRequest, RequestOptions.DEFAULT);

        System.out.println(indexResponse);
    }
    //获取索引 GET /技嘉
    @Test
    void testGetIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("技嘉");
        //判断索引是否存在
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("技嘉");
        //删除
        AcknowledgedResponse exists = client.indices().delete(request,RequestOptions.DEFAULT);
        System.out.println(exists);
        boolean acknowledged = exists.isAcknowledged();
        System.out.println(acknowledged);
    }
    //添加文档
    @Test
    void testAddDocument() throws IOException {
        User zhangsan = new User(1, "zhangsan");
        //创建请求
        IndexRequest request = new IndexRequest("技嘉");
        //设置规则 PUT /技嘉/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        //将数据放入到json中
        request.source(JSON.toJSONString(zhangsan), XContentType.JSON);
        //客户端发送请求,获取响应的结果
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());//对应命令返回的状态
    }
    //获取文档,判断是否存在
    @Test
    void testIsExistDocument() throws IOException {
        GetRequest request=new GetRequest("技嘉","1");
        //不获取返回的_source的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //获取文档信息
    @Test
    void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("技嘉", "1");
        GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsString());//打印文档的内容
        System.out.println(documentFields);//获取文档全部内容
    }
    //更新请求
    @Test
    void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("技嘉","1");

        User user = new User(2, "李四");
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update.status());
    }
    //删除文档
    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("技嘉","1");
        deleteRequest.timeout(TimeValue.timeValueSeconds(1));
        DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }
    //生产会批量导入数据
    //更新请求
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(1));

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(3,"a"));
        userList.add(new User(4,"b"));
        userList.add(new User(5,"c"));
        userList.add(new User(6,"d"));
        userList.add(new User(7,"e"));
        userList.add(new User(8,"f"));
        userList.add(new User(9,"g"));
        userList.add(new User(0,"h"));

        for (int i = 0; i < userList.size(); i++) {
            //批量修改和删除只需要修改对应请求即可
            bulkRequest.add(new IndexRequest("技嘉")
//                    .id(""+(i+3))
                    .source(JSON.toJSONString(userList.get(i)),XContentType.JSON)
            );
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
    }
    //查询 QueryBuilders工具类
    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("技嘉");
        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", "c"));
        //QueryBuilders.termQuery 精确查询
        //QueryBuilders.matchAllQuery 匹配所有
        //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "d");
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(1));
        //高亮
        //searchSourceBuilder.highlighter();
        //分页，都有默认值
        //searchSourceBuilder.from();
        //searchSourceBuilder.size();
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        System.out.println(JSON.toJSONString(hits));
        System.out.println("==================================");
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }
}
