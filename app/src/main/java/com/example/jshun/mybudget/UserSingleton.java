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
        expenseCategories.add(tuition);
        BudgetItem berks = new BudgetItem("Books", (float) 200.00);
        expenseCategories.add(berks);
        BudgetItem job = new BudgetItem("Work", (float) 800.00);
        incomeCategories.add(job);
        BudgetItem rent = new BudgetItem("Rent", (float) 350.00);
        expenseCategories.add(rent);
        BudgetItem food = new BudgetItem("Food", (float) 200.00);
        expenseCategories.add(food);
    }

    public static UserSingleton Instance(){
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }
}
