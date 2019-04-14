package com.xuyao.elasticsearch.service.impl;


import com.xuyao.elasticsearch.model.News;
import com.xuyao.elasticsearch.repository.NewsRepository;
import com.xuyao.elasticsearch.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsRepository, News> implements NewsService {

}
