package com.xuyao.elasticsearch.service;


import java.util.List;

public interface BaseService<T> {

    boolean deleteIndex(Class<T> clazz);

    boolean deleteIndex(String indexName);

    T findById(String id);

    T save(T t);

    List<T> saveAll(Iterable<T> iterable);

    List<T> findAll();

    void deleteAll();
}
