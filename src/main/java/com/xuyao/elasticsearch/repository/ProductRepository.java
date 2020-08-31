package com.xuyao.elasticsearch.repository;


import com.xuyao.elasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product,String> {
}