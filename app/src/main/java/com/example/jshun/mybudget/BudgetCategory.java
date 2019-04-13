package com.example.jshun.mybudget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class BudgetCategory {


    private String categoryName;
    private boolean isMonthly;
    private float totalAmount;
    private float monthlyAmount;
    private float totalExpended;
    private float amountRemaining;

    private ArrayList<transactionItem> transactions;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
        this.monthlyAmount = totalAmount / 4;
        recalculate();
    }

    public float getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(float monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
        this.totalAmount = monthlyAmount * 4;
        recalculate();
    }

    public float getTotalExpended() {
        return totalExpended;
    }

    public float getAmountRemaining() {
        return amountRemaining;
    }

    public ArrayList<transactionItem> getTransactions() {
        return transactions;
    }

    public void addTransaction(transactionItem newItem){
        //put the thing in the list
        //sort the list
        //calculate new expended and remaining amounts
        transactions.add(newItem);
        recalculate();
    }

    public void deleteTransaction(int index){
        transactions.remove(index);
        recalculate();
    }
    
    private void recalculate(){
        Collections.sort(transactions);
        float runningTotal = 0;
        for (transactionItem item: transactions){
            runningTotal += item.getAmount();
        }
        totalExpended = runningTotal;
        amountRemaining = totalAmount - totalExpended;
    }


}
