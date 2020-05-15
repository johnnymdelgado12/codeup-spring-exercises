package com.codeup.springblogapp.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 250)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    public User(){};

}
