package com.timothy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class OrderEN {

    @Id
    @GeneratedValue
    Integer orderId;
    Integer customerId;
    Integer totalBill;

    public OrderEN() {
    }

    public OrderEN(Integer customerId, Integer totalBill) {
        this.customerId = customerId;
        this.totalBill = totalBill;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Integer totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId=" + customerId +
                ", totalBill=" + totalBill +
                '}';
    }
}
