package com.madshines.content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Author:madshines
 * Date:2020/6/9
 * Package:com.madshines.content
 * Description:网站爬取
 **/
public class Content {
//    public static void contentLists() throws IOException {
//        String url="https://s.taobao.com/search?q=java";
//        Document document = Jsoup.parse(new URL(url), 30000);
//        Element mainsrpItemlist = document.getElementById("mainsrp-itemlist");
//        Elements item_j_mouserOnverReq = mainsrpItemlist.getElementsByClass("item J_MouserOnverReq");
//        for (Element element : item_j_mouserOnverReq) {
//            String img = element.getElementsByTag("img").eq(0).attr("src");
//            String title = element.getElementsByClass("row").eq(0).text();
//            String price = element.getElementsByClass("price").eq(0).text();
//            System.out.println(img);
//            System.out.println(title);
//            System.out.println(price);
//        }
//    }

    public static void main(String[] args) throws IOException {
        String url="https://s.taobao.com/search?q=java";
        Document document = Jsoup.parse(new URL(url), 30000);
        Element mainsrpItemlist = document.getElementById("page");
        //System.out.println(mainsrpItemlist);
//        Elements allElements = document.getAllElements();
//        for (Element allElement : allElements) {
//            System.out.println(allElement);
//        }
//        System.out.println(mainsrpItemlist);
        Elements item_j_mouserOnverReq = mainsrpItemlist.getElementsByClass("items");
        for (Element element : item_j_mouserOnverReq) {
            String img = element.getElementsByTag("img").eq(0).attr("src");
            String title = element.getElementsByClass("row").eq(0).text();
            String price = element.getElementsByClass("price").eq(0).text();
            System.out.println(img);
            System.out.println(title);
            System.out.println(price);
        }
    }
}
