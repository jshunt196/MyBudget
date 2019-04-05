package com.example.jshun.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toolbar;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class TransactionsActivity extends AppCompatActivity {
    ArrayList<String> catagories = new ArrayList<>();
    UserSingleton theStuff = UserSingleton.Instance();
    ArrayList<BudgetItem> oneTimeExpenses = theStuff.getOneTimeExpenses();
    ArrayList<BudgetItem> recurringExpenses = theStuff.getRecurringExpenses();

    public TransactionsActivity() {
        catagories.add("Housing");
        catagories.add("Gas");
        catagories.add("Other Stuff!!");
        catagories.add("Other Stuff!!");
        catagories.add("Other Stuff!!");
        catagories.add("Other Stuff!!");
        catagories.add("Other Stuff!!");
        catagories.add("Other Stuff!!");
        catagories.add("Other Stuff!!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: stuff here!
        setContentView(R.layout.transactions_activity);

        RecyclerView recyclerExpenditures = findViewById(R.id.expRecycler);
        recyclerExpenditures.setAdapter(new ExpendituresAdapter(this, recurringExpenses));
        recyclerExpenditures.setLayoutManager(new LinearLayoutManager(this));
    }

    public void initRecyclers() {
        // TODO: build this!!
    }

}
