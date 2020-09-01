package com.xuyao.elasticsearch.service;


import java.util.List;

public interface BaseService<T> {

    boolean createIndex(Class clazz);

    boolean createIndex(String indexName);

    boolean deleteIndex(Class clazz);

    boolean deleteIndex(String indexName);

    T findById(String id);

    T save(T t);

    List<T> saveAll(Iterable<T> iterable);

    List<T> findAll();

    void deleteAll();

    void deleteById(String id);

}
