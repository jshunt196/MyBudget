package com.example.jshun.mybudget;

import java.util.ArrayList;

public class UserSingleton {

    private static UserSingleton instance;

    private ArrayList<BudgetItem> transactions;

    private UserSingleton(){
        transactions = new ArrayList<BudgetItem>();
    }

    public static UserSingleton Instance(){
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }
}
