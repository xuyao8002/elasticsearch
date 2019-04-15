package com.xuyao.elasticsearch;

import com.xuyao.elasticsearch.model.News;
import com.xuyao.elasticsearch.service.NewsService;
import org.elasticsearch.common.UUIDs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private NewsService newsService;

    @Test
    public void deleteIndex (){
        String indexName = "xuyao";
        System.out.println(newsService.deleteIndex(indexName));
        Class<News> newsClass = News.class;
        System.out.println(newsService.deleteIndex(newsClass));

    }

    @Test
    public void saveAll(){
        int all = 100;
        long start = System.currentTimeMillis();
        for (int i = 0; i < all; i++) {
            News news = new News();
            news.setId(String.valueOf(i));
            news.setContent("这是测试content");
            news.setDescription("测试描述");
            Date now = new Date();
            news.setCreateDate(now);
            news.setUpdateDate(now);
            news.setTitle("test标题");
            news = newsService.save(news);
        }
        System.out.println("save end: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        List<News> list = new ArrayList<>(all);
        for (int i = all; i < all *2; i++) {
            News news = new News();
            news.setId(String.valueOf(i));
            news.setContent("这是测试content");
            news.setDescription("测试描述");
            Date now = new Date();
            news.setCreateDate(now);
            news.setUpdateDate(now);
            news.setTitle("test标题");
            list.add(news);
        }
        List<News> news = newsService.saveAll(list);

        System.out.println("saveAll end: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void save() {
        News news = new News();
        news.setId(UUIDs.base64UUID());
        news.setContent("这是测试content");
        news.setDescription("测试描述");
        Date now = new Date();
        news.setCreateDate(now);
        news.setUpdateDate(now);
        news.setTitle("test标题");
            News save = newsService.save(news);
            System.out.println(news);
    }

    @Test
    public void find() {
        News byId = newsService.findById("JPaMHmoBBwA5Sdyd3pge");
        System.out.println("find: "+byId);
    }

    @Test
    public void findAll(){
        List<News> all = newsService.findAll();
        all.forEach(n -> System.out.println(n));
        System.out.println("findAll end");
    }

    @Test
    public void size(){
        System.out.println(newsService.findAll().size());
    }

    @Test
    public void deleteAll(){
        newsService.deleteAll();
    }

}
