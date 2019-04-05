package com.example.jshun.mybudget;

import java.util.ArrayList;

public class UserSingleton {

    private static UserSingleton instance;

    private ArrayList<BudgetItem> oneTimeExpenses;
    private ArrayList<BudgetItem> recurringExpenses;
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
    }

    public static UserSingleton Instance(){
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }
}
