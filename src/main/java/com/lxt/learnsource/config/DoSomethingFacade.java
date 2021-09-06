package com.lxt.learnsource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DoSomethingFacade {

    @Autowired
    private ComponentBean componentBean;

    public void doSomething() {
        componentBean.doComponent();
    }

}
