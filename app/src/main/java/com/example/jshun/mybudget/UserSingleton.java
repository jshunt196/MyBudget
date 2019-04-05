package com.example.jshun.mybudget;

import java.util.ArrayList;

public class UserSingleton {

    private static UserSingleton instance;

    public ArrayList<BudgetItem> oneTimeExpenses;
    public ArrayList<BudgetItem> recurringExpenses;
    private ArrayList<String> Categories;

    public ArrayList<BudgetItem> getOneTimeExpenses() {
        return oneTimeExpenses;
    }

    public void setOneTimeExpenses(ArrayList<BudgetItem> oneTimeExpenses) {
        this.oneTimeExpenses = oneTimeExpenses;
    }

    public ArrayList<BudgetItem> getRecurringExpenses() {
        return recurringExpenses;
    }

    public void setRecurringExpenses(ArrayList<BudgetItem> recurringExpenses) {
        this.recurringExpenses = recurringExpenses;
    }

    public ArrayList<String> getCategories() {
        return Categories;
    }

    public void setCategories(ArrayList<String> categories) {
        Categories = categories;
    }

    private UserSingleton(){
        oneTimeExpenses = new ArrayList<BudgetItem>();
        recurringExpenses = new ArrayList<BudgetItem>();
        //create dummy data
        //tuition, job, rent, food
        BudgetItem tuition = new BudgetItem("Tuition", (float) -2500.00);
        oneTimeExpenses.add(tuition);
        BudgetItem berks = new BudgetItem("Books", (float) -200.00);
        oneTimeExpenses.add(berks);
        BudgetItem job = new BudgetItem("Work", (float) 800.00);
        recurringExpenses.add(job);
        BudgetItem rent = new BudgetItem("Rent", (float) -350.00);
        recurringExpenses.add(rent);
        BudgetItem food = new BudgetItem("Food", (float) -200.00);
        recurringExpenses.add(food);
    }

    public static UserSingleton Instance(){
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }
}
