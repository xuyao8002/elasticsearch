package com.xuyao.elasticsearch.service.impl;


import com.xuyao.elasticsearch.model.Product;
import com.xuyao.elasticsearch.repository.ProductRepository;
import com.xuyao.elasticsearch.service.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductRepository, Product> implements IProductService {

}
