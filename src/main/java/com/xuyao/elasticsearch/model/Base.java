package com.xuyao.elasticsearch.model;

import org.springframework.data.annotation.Id;

public class Base {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
