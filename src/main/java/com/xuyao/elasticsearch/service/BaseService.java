package com.xuyao.elasticsearch.service;


import java.util.List;

public interface BaseService<T> {

    T findById(String id);

    T save(T t);

    Iterable<T> saveAll(Iterable<T> iterable);

    List<T> findAll();

    void deleteAll();
}
