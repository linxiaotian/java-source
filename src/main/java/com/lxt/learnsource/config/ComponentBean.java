package com.lxt.learnsource.config;

import org.springframework.stereotype.Component;

@Component
public class ComponentBean implements ComponentParent {

    @Override
    public void doComponent() {
         System.out.println("do component");
    }

}
