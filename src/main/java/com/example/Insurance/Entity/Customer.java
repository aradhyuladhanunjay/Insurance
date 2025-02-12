package com.example.Insurance.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id", nullable = false, length = 45)
    private String id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "mobile", nullable = false, length = 15)
    private String mobile;

    @Column(name = "username", nullable = false, unique = true, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "age", nullable = false)
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}