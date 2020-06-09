package com.madshines.controller;

import com.madshines.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author :madshines
 * @Date: 2020-06-09
 * @Description: com.madshines.controller
 * @version: 1.0
 */
@RestController
public class ContentController {
    @Autowired
    ContentService contentService;
    @RequestMapping("save/{keyword}")
    public Boolean saveContent(@PathVariable("keyword") String keyword) throws IOException {
        return contentService.parseContent(keyword);
    }
    @RequestMapping("search/{keyword}/{pageno}/{pagesize}")
    public List<Map<String, Object>> search(@PathVariable("keyword") String keyword,
                                            @PathVariable("pageno") int pageno,
                                            @PathVariable("pagesize") int pagesize) throws IOException {
        return contentService.searchPage(keyword,pageno,pagesize);
    }
}
