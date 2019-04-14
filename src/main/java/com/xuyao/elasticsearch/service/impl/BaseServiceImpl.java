package com.xuyao.elasticsearch.service.impl;


import com.xuyao.elasticsearch.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.ArrayList;
import java.util.List;

public class BaseServiceImpl<R extends ElasticsearchRepository<T, String>, T> implements BaseService<T> {

    @Autowired
    private R repository;

    @Override
    public T findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> iterable) {
        return repository.saveAll(iterable);
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
