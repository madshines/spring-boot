package com.madshines;

import com.alibaba.fastjson.JSON;
import com.madshines.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
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
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class Springbootes1ApplicationTests {

	@Autowired
	@Qualifier("restHighLevelClient")
	private RestHighLevelClient client;

	@Test
	void createIndex() throws IOException {
		CreateIndexRequest createIndexRequest=new CreateIndexRequest("user");
		CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		System.out.println(createIndexResponse);
	}
	@Test
	void findIndex() throws IOException {
		GetIndexRequest getIndexRequest=new GetIndexRequest("test3");
//		判断index是否存在
//		boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
//		System.out.println(exists);
		GetIndexResponse indexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
//		查看index的settings单个属性值
//		String user = indexResponse.getSetting("user", "index.creation_date");
//		System.out.println(user);
//		查看index的settings的所有属性值
//		Map<String, Settings> settings = indexResponse.getSettings();
//		Set<Map.Entry<String, Settings>> entries = settings.entrySet();
//		for (Map.Entry<String, Settings> entry : entries) {
//			String str = entry.getKey()+"-"+entry.getValue();
//			System.out.println(str);
//		}
//		获取index的所有mapping信息
		Map<String, MappingMetaData> mappings = indexResponse.getMappings();
		Set<Map.Entry<String, MappingMetaData>> entries = mappings.entrySet();
		for (Map.Entry<String, MappingMetaData> entry : entries) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
			Map<String, Object> sourceAsMap = entry.getValue().getSourceAsMap();
			Set<Map.Entry<String, Object>> entries1 = sourceAsMap.entrySet();
			for (Map.Entry<String, Object> stringObjectEntry : entries1) {
				String s=stringObjectEntry.getKey()+" "+stringObjectEntry.getValue();
				System.out.println(s);
			}
		}
	}
	@Test
	void deleteIndex() throws IOException {
		DeleteIndexRequest deleteIndexRequest=new DeleteIndexRequest("user");
		AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
		System.out.println(delete.isAcknowledged());
	}
	/*
	* ------------------------------------------------------------------------------------------------------------------
	* */
	@Test
	void putDocument() throws IOException {
		IndexRequest indexRequest=new IndexRequest("user");
		IndexRequest source = indexRequest.source(JSON.toJSONString(new User(2, "jack", "男")), XContentType.JSON);
		IndexRequest id = indexRequest.id("1");
		IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
		RestStatus status = index.status();
		System.out.println(status);
	}
	@Test
	void getDecument() throws IOException {
		GetRequest getRequest=new GetRequest("user","1");
		GetResponse documentFields = client.get(getRequest, RequestOptions.DEFAULT);
		String sourceAsString = documentFields.getSourceAsString();
		System.out.println(sourceAsString);
	}
	@Test
	void deleteDocument() throws IOException {
		DeleteRequest deleteRequest=new DeleteRequest("user","zoxmlHIBcAtWHMWhXItn");
		DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
		RestStatus status = delete.status();
		System.out.println(status);
	}
	@Test
	void updateDocument() throws IOException {
		UpdateRequest updateRequest=new UpdateRequest("user","1");
		UpdateRequest updateRequest1 = updateRequest.doc(JSON.toJSONString(new User(1, "tom", "男")), XContentType.JSON);
		UpdateResponse update = client.update(updateRequest1, RequestOptions.DEFAULT);
		System.out.println(update.status());
	}
	/*
	* ------------------------------------------------------------------------------------------------------------------
	* */
	@Test
	void QueryDocument() throws IOException {
		SearchRequest searchRequest=new SearchRequest("user");
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("userName", "tom");
		searchSourceBuilder.query(matchQueryBuilder);
		searchRequest.source(searchSourceBuilder);
		SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = search.getHits();
		SearchHit[] hits1 = hits.getHits();
		for (SearchHit documentFields : hits1) {
			System.out.println(documentFields);
		}
	}
}
