package com.madshines.service;

import com.madshines.pojo.Content;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author :madshines
 * @Date: 2020-06-09
 * @Description: com.madshines.service
 * @version: 1.0
 */
public interface ContentService {
    //将解析数据存到数据库中
    public void saveContent(Content content);
    //解析数据放入到elasticsearch中
    public boolean parseContent(String keyWord) throws IOException;
    //获取数据实现搜索功能
    public List<Map<String,Object>> searchPage(String keyWord,int pageNo,int pageSize) throws IOException;
}
