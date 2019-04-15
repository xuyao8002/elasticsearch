package com.xuyao.elasticsearch.service.impl;


import com.xuyao.elasticsearch.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class BaseServiceImpl<R extends ElasticsearchRepository<T, String>, T> implements BaseService<T> {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private R repository;

    @Override
    public boolean deleteIndex(Class<T> clazz){
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
