package com.lxt.learnsource.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = -190148835810510112L;

    private int id;

    private String name;

}
