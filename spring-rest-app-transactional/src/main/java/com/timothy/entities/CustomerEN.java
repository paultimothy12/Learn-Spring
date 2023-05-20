package com.timothy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerEN {

    @Id
    Integer customerId;
    String name;
    Integer credit;

    public CustomerEN() {
    }

    public CustomerEN(Integer customerId, String name, Integer credit) {
        this.customerId = customerId;
        this.name = name;
        this.credit = credit;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                '}';
    }
}
