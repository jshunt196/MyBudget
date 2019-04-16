package com.example.jshun.mybudget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AddTransactionFrag extends Activity implements AdapterView.OnItemSelectedListener {
    UserSingleton theStuff = UserSingleton.Instance();
    private List<String> expensesCategories = new ArrayList<String>();;
    private Date calendarDate;
    private boolean isIncome;
    private int index;
    BudgetCategory thisBudgetCategory;

    public AddTransactionFrag() {
        for (BudgetCategory i : theStuff.getExpenseCategories()) {
            expensesCategories.add(i.getCategoryName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_transaction_layout);

        Bundle bundle = getIntent().getExtras();
        isIncome = bundle.getBoolean("income");
        index = bundle.getInt("index");
        if(isIncome){
            thisBudgetCategory = theStuff.incomeCategories.get(index);
        }
        else {
            thisBudgetCategory = theStuff.expenseCategories.get(index);
        }

        final EditText description = findViewById(R.id.descriptionTran);
        final EditText amount = findViewById(R.id.amountTran);
        CalendarView date = (CalendarView) findViewById(R.id.calendarView);
        date.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendarDate = new Date( year, month, dayOfMonth );
            }
        });

        Spinner spinner = findViewById(R.id.categorySpin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, expensesCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(AddTransactionFrag.this);
        spinner.setSelection(index);
        final String spinnerString = spinner.getSelectedItem().toString();

        Button saveButton = findViewById(R.id.saveTransaction);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                transactionItem newItem = new transactionItem();
                newItem.setName(description.getText().toString());
                newItem.setAmount(Float.valueOf(amount.getText().toString()));
                newItem.setDate(calendarDate);
                for (BudgetCategory myItem : theStuff.getExpenseCategories()) {
                    if (myItem.getCategoryName() == spinnerString) {
                        myItem.addTransaction(newItem);
                    }
                }
                startActivity(new Intent(AddTransactionFrag.this, TransactionsActivity.class));
            }
        });

        Button cancelButton = findViewById(R.id.cancelTransaction);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //create budget item and send it to the singleton
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String textStuff = adapterView.getItemAtPosition(i).toString();
        Toast toast = Toast.makeText(this, "Selected " + textStuff, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String textStuff = adapterView.getItemAtPosition(i).toString();
//        Toast toast = Toast.makeText(this, textStuff, Toast.LENGTH_LONG);
//        toast.show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}