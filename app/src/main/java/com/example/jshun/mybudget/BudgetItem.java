package com.example.jshun.mybudget;

import java.util.Date;

public class BudgetItem implements Comparable<BudgetItem> {
    private float amount;
    private String category;
    private Date date;
    private boolean monthly;

    public BudgetItem() {
    }

    public BudgetItem(String cat, float amt){
        this.category = cat;
        this.amount = amt;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getFrequency() { return monthly; }

    public void setFrequency(boolean isMonthly) { monthly = isMonthly; }

    @Override
    public int compareTo(BudgetItem o) {
        return getDate().compareTo(o.getDate());
    }
}
