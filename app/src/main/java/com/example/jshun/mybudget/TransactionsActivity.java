package com.example.jshun.mybudget;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
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

        float semesterIncome = theStuff.getSemesterIncome();
        float semesterExpenses = theStuff.getSemesterExpenses();
        float semesterBudgetedExpenses = theStuff.getSemsterBudgetedExpenses();
        float remainderBudgeted = semesterBudgetedExpenses - semesterExpenses;
        TextView incomeView = findViewById(R.id.IncomeView);
        incomeView.setText(String.valueOf(semesterIncome));
        TextView remainder = findViewById(R.id.BudgetRemainingView);
        if (remainderBudgeted > 0) {
            remainder.setTextColor(Color.parseColor("#4CAF50"));
        }
        else if (remainderBudgeted < 0) {
            remainder.setTextColor(Color.parseColor("#F44336"));
        }
        remainder.setText(String.valueOf(remainderBudgeted));
        TextView totalBudgeted = findViewById(R.id.totalBudgetedView);
        totalBudgeted.setText(String.valueOf(semesterBudgetedExpenses));

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
                Intent i = new Intent(TransactionsActivity.this, AddTransactionFrag.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("income", false);
                bundle.putInt("index", 0);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    public void initRecyclers() {
        // TODO: build this!!
    }

}
