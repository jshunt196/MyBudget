package com.example.jshun.mybudget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AddTransactionFrag extends Activity implements AdapterView.OnItemSelectedListener {
    UserSingleton theStuff = UserSingleton.Instance();
    private List<String> expensesCategories = new ArrayList<String>();;
    private Date calendarDate = Calendar.getInstance().getTime();
    private boolean isIncome;
    private int index;
    BudgetCategory thisBudgetCategory;

    private boolean hasName;
    private boolean hasAmt;
    Button saveButton;

    public AddTransactionFrag() {
        for (BudgetCategory i : theStuff.getExpenseCategories()) {
            expensesCategories.add(i.getCategoryName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_transaction_layout);

        hasName = false;
        hasAmt = false;

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
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0){
                    hasName = true;
                    validate();
                }
                else{
                    hasName = false;
                    validate();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        final EditText amount = findViewById(R.id.amountTran);
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0){
                    hasAmt = true;
                    validate();
                }
                else{
                    hasAmt = false;
                    validate();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        CalendarView date = (CalendarView) findViewById(R.id.calendarView);
        date.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendarDate = new Date( year, month, dayOfMonth );
            }
        });

        final Spinner spinner = findViewById(R.id.categorySpin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, expensesCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(index);
        spinner.setOnItemSelectedListener(AddTransactionFrag.this);

        saveButton = findViewById(R.id.saveTransaction);
        saveButton.setEnabled(false);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                final String spinnerString = spinner.getSelectedItem().toString();
                transactionItem newItem = new transactionItem();
                newItem.setName(description.getText().toString());
                newItem.setAmount(Float.valueOf(amount.getText().toString()));
                newItem.setDate(calendarDate);
                for (BudgetCategory myItem : theStuff.getExpenseCategories()) {
                    if (myItem.getCategoryName().equals(spinnerString)) {
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

    private void validate(){
        if (hasName && hasAmt) {
            saveButton.setEnabled(true);
        }
        else{
            saveButton.setEnabled(false);
        }
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