package com.lxt.learnsource.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SchoolAutoCongituration.class)
public @interface EnableSchool {
}
