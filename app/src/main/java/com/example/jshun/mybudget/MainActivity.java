package com.example.jshun.mybudget;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar mActionBarToolbar;

    UserSingleton theStuff = UserSingleton.Instance();

    ArrayList<BudgetItem> oneTimeExpenses = theStuff.getOneTimeExpenses();
    ArrayList<BudgetItem> recurringExpenses = theStuff.getRecurringExpenses();

    private static final String TAG = "MainActivity";

    public MainActivity() {
        /*
        incomes.add("Monthly Income");
        incomes.add("External Support");
        incomes.add("Student Loans");

        semesterExpense.add("Tuition");
        semesterExpense.add("Books and Fees");
        semesterExpense.add("Apartment Deposit");

        expenses.add("Food");
        expenses.add("Housing");
        expenses.add("Transportation");
        expenses.add("School");
        expenses.add("Clothing");
        expenses.add("Recreational");
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        //Toolbar toolbar = (Toolbar) findViewById(R.menu.toolbar_map);

        Button semesterButton = findViewById(R.id.semesterButton);
        semesterButton.setEnabled(true);
        semesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //Toast toast = Toast.makeText(getApplicationContext(), "Finished!!!", Toast.LENGTH_LONG);
                //toast.show();
                Intent i = new Intent(MainActivity.this, Pop.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("once", true);
                bundle.putInt("index", -1);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        Button monthlyButton = findViewById(R.id.monthlyButton);
        monthlyButton.setEnabled(true);
        monthlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //Toast toast = Toast.makeText(getApplicationContext(), "Finished!!!", Toast.LENGTH_LONG);
                //toast.show();
                Intent i = new Intent(MainActivity.this, Pop.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("once", false);
                bundle.putInt("index", -1);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        Button finishedButton = findViewById(R.id.finishedButton);
        finishedButton.setEnabled(true);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //Toast toast = Toast.makeText(getApplicationContext(), "Finished!!!", Toast.LENGTH_LONG);
                //toast.show();
                Intent i = new Intent(MainActivity.this, TransactionsActivity.class);
                startActivity(i);
            }
        });

        initRecyclers();

        /*Button incomeButton = findViewById(R.id.incomeButton);
        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Toast toast = Toast.makeText(getApplicationContext(), "Finished!!!", Toast.LENGTH_LONG);
                toast.show();

                //Fragment fragment = new
            }
        });*/
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_map, menu);
    }*/

    private void refreshData(){

    }

    private void initRecyclers() {
        Log.d(TAG, "initRecycler: init");

        RecyclerView recyclerSemester = findViewById(R.id.semesterRecycle);
        recyclerSemester.setAdapter(new ExpensesAdapter(this, oneTimeExpenses));
        recyclerSemester.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView recyclerExpense = findViewById(R.id.expensesRecycle);
        recyclerExpense.setAdapter(new ExpensesAdapter(this, recurringExpenses));
        recyclerExpense.setLayoutManager(new LinearLayoutManager(this));
    }
}
