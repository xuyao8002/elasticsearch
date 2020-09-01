package com.xuyao.elasticsearch;

import com.xuyao.elasticsearch.model.News;
import com.xuyao.elasticsearch.model.Product;
import com.xuyao.elasticsearch.service.INewsService;
import com.xuyao.elasticsearch.service.IProductService;
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
    private INewsService newsService;

    @Autowired
    private IProductService productService;

    @Test
    public void createIndex(){
        newsService.createIndex(News.class);
    }

    @Test
    public void createIndex1(){
        productService.createIndex(Product.class);
    }

    @Test
    public void deleteIndex (){
        System.out.println("delete index by indexName：" + newsService.deleteIndex("news"));
        System.out.println("delete index by class：" + productService.deleteIndex(Product.class));
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

    @Test
    public void deleteById(){
        newsService.deleteById("id");
    }

}
