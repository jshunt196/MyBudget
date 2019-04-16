package com.example.jshun.mybudget;

import java.util.ArrayList;

public class UserSingleton {

    private static UserSingleton instance;

    public ArrayList<BudgetCategory> incomeCategories;
    public ArrayList<BudgetCategory> expenseCategories;

    public ArrayList<BudgetCategory> getIncomeCategories() {
        return incomeCategories;
    }

    public void getIncomeCategories(ArrayList<BudgetCategory> oneTimeExpenses) {
        this.incomeCategories = oneTimeExpenses;
    }

    public ArrayList<BudgetCategory> getExpenseCategories() {
        return expenseCategories;
    }

    public void setExpenseCategories(ArrayList<BudgetCategory> recurringExpenses) {
        this.expenseCategories = recurringExpenses;
    }

    private UserSingleton(){
        incomeCategories = new ArrayList<BudgetCategory>();
        expenseCategories = new ArrayList<BudgetCategory>();
        //create dummy data
        //tuition, job, rent, food
        BudgetCategory tuition = new BudgetCategory("Tuition", false, (float)2500.00);
        expenseCategories.add(tuition);
        BudgetCategory berks = new BudgetCategory("Books", false, (float)200.00);
        expenseCategories.add(berks);
        BudgetCategory job = new BudgetCategory("Work", true,(float)850.00);
        incomeCategories.add(job);
        BudgetCategory rent = new BudgetCategory("Rent", true, (float)350.00);
        expenseCategories.add(rent);
        BudgetCategory food = new BudgetCategory("Food", true, (float)200.00);
        expenseCategories.add(food);
    }

    public static UserSingleton Instance(){
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }

    public float getSemesterIncome(){
        float totalIncome = 0;
        for (BudgetCategory current : incomeCategories){
            totalIncome += current.getTotalAmount();
        }
        return totalIncome;
    }
    public float getSemsterBudgetedExpenses(){
        float totalBudgetedExpenses = 0;
        for (BudgetCategory current : expenseCategories){
            totalBudgetedExpenses += current.getTotalAmount();
        }
        return totalBudgetedExpenses;
    }
    public float getSemesterExpenses(){
        float totalExpenses = 0;
        for (BudgetCategory current : expenseCategories){
            totalExpenses += current.getTotalExpended();
        }
        return totalExpenses;
    }
}
