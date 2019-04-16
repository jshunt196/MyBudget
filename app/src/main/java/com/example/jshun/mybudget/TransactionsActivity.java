package com.example.jshun.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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
    ArrayList<BudgetCategory> oneTimeExpenses = theStuff.getIncomeCategories();
    ArrayList<BudgetCategory> expenseCategories = theStuff.getExpenseCategories();

    public TransactionsActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: stuff here!
        setContentView(R.layout.transactions_activity);

        RecyclerView recyclerExpenditures = findViewById(R.id.expRecycler);
        recyclerExpenditures.setAdapter(new ExpendituresAdapter(this, false));
        recyclerExpenditures.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerExpenditures.addItemDecoration(itemDecoration);

        Button addTransactionButton = findViewById(R.id.addTransactionButton);
        addTransactionButton.setEnabled(true);
        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //Toast toast = Toast.makeText(getApplicationContext(), "Finished!!!", Toast.LENGTH_LONG);
                //toast.show();
                Intent i = new Intent(TransactionsActivity.this, AddTransactionFrag.class);
                startActivity(i);
            }
        });
    }

    public void initRecyclers() {
        // TODO: build this!!
    }

}
