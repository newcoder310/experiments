package com.experiments.microservices.dbservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="quote", catalog = "demo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String quote;

    public Quote(String username, String quote) {
        this.username= username;
        this.quote=quote;
    }

}
