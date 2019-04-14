package com.example.jshun.mybudget;

import android.app.Activity;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddTransactionFrag extends Activity implements AdapterView.OnItemSelectedListener {
    UserSingleton theStuff = UserSingleton.Instance();
    private List<String> expensesCategories = new ArrayList<String>();;

    public AddTransactionFrag() {
        for (BudgetCategory i : theStuff.getExpenseCategories()) {
            expensesCategories.add(i.getCategoryName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_transaction_layout);

        Spinner spinner = findViewById(R.id.categorySpin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, expensesCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(AddTransactionFrag.this);

        Button saveButton = findViewById(R.id.saveTransaction);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                finish();
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