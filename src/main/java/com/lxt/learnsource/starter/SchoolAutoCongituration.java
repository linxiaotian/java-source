package com.lxt.learnsource.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@EnableConfigurationProperties(value = SchoolProperties.class)
@Configuration
public class SchoolAutoCongituration {

    @Bean
    public School getSchool(SchoolProperties properties) {
        SchoolProperties.SchoolMessage schoolMessage = properties.getOne();
        int totalKlass = Integer.parseInt(schoolMessage.getTotalKlass());
        List<Klass> klassList = new ArrayList<>(totalKlass);
        for (int i = 0; i < totalKlass; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName(i + "stuent");
            List<Student> students = new ArrayList<>();
            students.add(student);
            Klass klass = new Klass(students);
            klassList.add(klass);
        }
        return new School(klassList);
    }


}
