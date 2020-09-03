package com.xuyao.elasticsearch;

import com.xuyao.elasticsearch.model.News;
import com.xuyao.elasticsearch.model.Product;
import com.xuyao.elasticsearch.service.INewsService;
import com.xuyao.elasticsearch.service.IProductService;
import org.elasticsearch.common.UUIDs;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public void save() {
        News news = new News();
        Date now = new Date();
        news.setId(UUIDs.base64UUID());
        news.setContent("敦促美方恪守一个中国原则和中美联合公报规定");
        news.setDescription("恪守");
        news.setCreateDate(now);
        news.setUpdateDate(now);
        news.setTitle("一个中国原则");
        newsService.save(news);
        System.out.println(news);

        news.setId(UUIDs.base64UUID());
        news.setContent("美国大学限期驱逐中国公费留学生 外交部回应");
        news.setDescription("驱逐");
        news.setCreateDate(now);
        news.setUpdateDate(now);
        news.setTitle("中国公费留学生");
        newsService.save(news);
        System.out.println(news);

        news.setId(UUIDs.base64UUID());
        news.setContent("美媒曝解放军第8艘055万吨大驱下水 领先\"宙斯盾\"舰");
        news.setDescription("大驱");
        news.setCreateDate(now);
        news.setUpdateDate(now);
        news.setTitle("解放军第8艘055万吨大驱");
        newsService.save(news);
        System.out.println(news);

        news.setId(UUIDs.base64UUID());
        news.setContent("互联网时代，谁将成为“第一生产力”？");
        news.setDescription("第一生产力");
        news.setCreateDate(now);
        news.setUpdateDate(now);
        news.setTitle("互联网");
        newsService.save(news);
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

    @Test
    public void deleteById(){
        newsService.deleteById("id");
    }

    @Test
    public void pageSearch(){
        QueryBuilder builder;
        Page<News> page;
        List<News> content;
        //字段值作为短语直接匹配
        builder = QueryBuilders.matchPhraseQuery("content", "中国");
        page = newsService.findPage(builder, PageRequest.of(0, 10));
        content = page.getContent();
        System.out.println("matchPhraseQuery: " + content.size() + ", total: " + page.getTotalElements());
        page.getContent().forEach(e -> System.out.println(e));
        System.out.println();
        //字段值会被分词器分词后再匹配
        builder = QueryBuilders.matchQuery("content", "中国美国解放军");
        page = newsService.findPage(builder, PageRequest.of(0, 10));
        content = page.getContent();
        System.out.println("matchQuery: " + content.size() + ", total: " + page.getTotalElements());
        page.getContent().forEach(e -> System.out.println(e));
        System.out.println();
        //字段值在多个字段中匹配
        builder = QueryBuilders.multiMatchQuery("外交部", "content", "title");
        page = newsService.findPage(builder, PageRequest.of(0, 10));
        content = page.getContent();
        System.out.println("multiMatchQuery: " + content.size() + ", total: " + page.getTotalElements());
        page.getContent().forEach(e -> System.out.println(e));
    }

}
