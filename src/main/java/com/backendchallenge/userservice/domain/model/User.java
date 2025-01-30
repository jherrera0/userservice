package com.backendchallenge.userservice.domain.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String email;
    private String password;
    private String document;
    private String phone;
    private LocalDate birthdate;
    private String name;
    private String lastName;
    private String role;

    public User() {
    }

    public User( String email, String password, String document, String phone, LocalDate birthdate, String name,
                 String lastName) {
        this.email = email;
        this.password = password;
        this.document = document;
        this.phone = phone;
        this.birthdate = birthdate;
        this.name = name;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String name) {
        this.role = name;
    }

    public String getRole() {
        return role;
    }
}
