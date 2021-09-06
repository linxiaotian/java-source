package com.lxt.learnsource.starter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@Data
public class School {

    List<Klass> klass;

    public School(List<Klass> klass) {
        this.klass = klass;
    }

    public void have(){
        System.out.println("school have " + klass.size());
    }
    
}
