package com.lxt.learnsource.config;

import lombok.Data;

@Data
public class AssemblingBean {

    private String id;

    private String name;

    public String doAssembling() {
        return id + name;
    }

}
