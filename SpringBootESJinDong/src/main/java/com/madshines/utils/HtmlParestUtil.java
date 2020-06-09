package com.madshines.utils;

import com.madshines.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author :madshines
 * @Date: 2020-06-09
 * @Description: com.madshines.utils
 * @version: 1.0
 */
@Component
public class HtmlParestUtil {
//    public static void main(String[] args) throws IOException {
//        new HtmlParestUtil().parse("java").forEach(System.out::println);
//    }
    public List<Content> parse(String keywords) throws IOException {
        //获取请求  https://search.jd.com/Search?keyword=java
        //前提需要联网
        String url="https://search.jd.com/Search?keyword="+keywords;
        //解析网页 返回的Document就是页面对象
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有在js中使用的方法都可以用
        Element j_goodsList = document.getElementById("J_goodsList");
        //获取所有的li元素
        Elements li = j_goodsList.getElementsByTag("li");
        List<Content> goodList = new ArrayList<>();
        for (Element element : li) {
            String pic=element.getElementsByTag("img").eq(0).attr("src");
            String price = element.getElementsByClass("p-price").eq(0).text();
            String title=element.getElementsByClass("p-name").eq(0).text();
            Content content=new Content();
            content.setImg(pic);
            content.setPrice(price);
            content.setTitle(title);
            goodList.add(content);
        }
        return goodList;
    }
}
