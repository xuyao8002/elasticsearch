package com.xuyao.elasticsearch.repository;


import com.xuyao.elasticsearch.model.News;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NewsRepository extends ElasticsearchRepository<News,String> {
}