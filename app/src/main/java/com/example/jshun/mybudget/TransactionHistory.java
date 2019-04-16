package com.example.jshun.mybudget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends AppCompatActivity {
    UserSingleton theStuff = UserSingleton.Instance();
    private String title;
    private int spent;
    private int budget;
    private ArrayList<transactionItem> transactionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_tranhist);

        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("RowName");

        for (BudgetCategory i : theStuff.getExpenseCategories()) {
            if (i.getCategoryName().equals(title)) {
                transactionsList = i.getTransactions();
            }
        }

        RecyclerView recyclerExpenditures = findViewById(R.id.historyRecycler);
        recyclerExpenditures.setAdapter(new HistoryAdapter(this, transactionsList));
        recyclerExpenditures.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerExpenditures.addItemDecoration(itemDecoration);

        Button addTransactionButton = findViewById(R.id.addTranHistButton);
        addTransactionButton.setEnabled(true);
        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //Toast toast = Toast.makeText(getApplicationContext(), "Finished!!!", Toast.LENGTH_LONG);
                //toast.show();
                Intent i = new Intent(TransactionHistory.this, AddTransactionFrag.class);
                startActivity(i);
            }
        });

        spent = bundle.getInt("Spent");
        budget = bundle.getInt("Budget");

        TextView categoryName = findViewById(R.id.categoryExp);
        TextView categorySpent = findViewById(R.id.spentCat);
        TextView categoryBudget = findViewById(R.id.budgetCat);
        ProgressBar categoryProgress = findViewById(R.id.transactionProgress);
        categoryName.setText(title);
        categorySpent.setText("$"+String.valueOf(spent));
        categoryBudget.setText("$"+String.valueOf(budget));
        float floatSpent = spent;
        float floatBudget = budget;
        float progressAmount = floatSpent / floatBudget;
        progressAmount *= 100;
        categoryProgress.setProgress(Math.round(progressAmount));
    }
}
