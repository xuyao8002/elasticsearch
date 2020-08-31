package com.xuyao.elasticsearch.service.impl;


import com.xuyao.elasticsearch.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.ArrayList;
import java.util.List;

public class BaseServiceImpl<R extends ElasticsearchRepository<T, String>, T> implements BaseService<T> {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private R repository;

    @Override
    public boolean createIndex(Class clazz) {
        return elasticsearchTemplate.createIndex(clazz);
    }

    @Override
    public boolean createIndex(String indexName) {
        return elasticsearchTemplate.createIndex(indexName);
    }

    @Override
    public boolean deleteIndex(Class clazz){
        return elasticsearchTemplate.deleteIndex(clazz);
    }

    @Override
    public boolean deleteIndex(String indexName){
        return elasticsearchTemplate.deleteIndex(indexName);
    }

    @Override
    public T findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public List<T> saveAll(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        repository.saveAll(iterable).forEach(list::add);
        return list;
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void deleteAll(){
        repository.deleteAll();
    }


}
