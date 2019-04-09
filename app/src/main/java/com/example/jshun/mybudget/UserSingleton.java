package com.example.jshun.mybudget;

import java.util.ArrayList;

public class UserSingleton {

    private static UserSingleton instance;

    public ArrayList<BudgetItem> incomeCategories;
    public ArrayList<BudgetItem> expenseCategories;

    public ArrayList<BudgetItem> getIncomeCategories() {
        return incomeCategories;
    }

    public void getIncomeCategories(ArrayList<BudgetItem> oneTimeExpenses) {
        this.incomeCategories = oneTimeExpenses;
    }

    public ArrayList<BudgetItem> getExpenseCategories() {
        return expenseCategories;
    }

    public void setExpenseCategories(ArrayList<BudgetItem> recurringExpenses) {
        this.expenseCategories = recurringExpenses;
    }

    private UserSingleton(){
        incomeCategories = new ArrayList<BudgetItem>();
        expenseCategories = new ArrayList<BudgetItem>();
        //create dummy data
        //tuition, job, rent, food
        BudgetItem tuition = new BudgetItem("Tuition", (float) 2500.00);
        tuition.setFrequency(false);
        expenseCategories.add(tuition);
        BudgetItem berks = new BudgetItem("Books", (float) 200.00);
        tuition.setFrequency(false);
        expenseCategories.add(berks);
        BudgetItem job = new BudgetItem("Work", (float) 3200.00);
        job.setFrequency(true);
        incomeCategories.add(job);
        BudgetItem rent = new BudgetItem("Rent", (float) 1400.00);
        rent.setFrequency(true);
        expenseCategories.add(rent);
        BudgetItem food = new BudgetItem("Food", (float) 800.00);
        food.setFrequency(true);
        expenseCategories.add(food);
    }

    public static UserSingleton Instance(){
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }
}
