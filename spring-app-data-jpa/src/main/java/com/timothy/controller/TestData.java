package com.timothy.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TestData {
    public TestData() {
    }
    @Id
    @GeneratedValue
    Integer id;
    Integer number;
    String name;

    public TestData(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
