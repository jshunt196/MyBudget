package com.example.jshun.mybudget;

import java.util.Date;

public class transactionItem implements Comparable<transactionItem> {

    private float amount;
    private String name;
    private Date date;


    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(transactionItem o) {
        return getDate().compareTo(o.getDate());
    }

}
