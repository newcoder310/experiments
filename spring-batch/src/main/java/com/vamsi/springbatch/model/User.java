package com.vamsi.springbatch.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private Integer id;
    private String name;
    private String dept;
    private Integer salary;
}
