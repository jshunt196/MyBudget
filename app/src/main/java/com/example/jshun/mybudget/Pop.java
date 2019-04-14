package com.example.jshun.mybudget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Pop extends Activity {

    boolean isIncome;
    int arrayIndex;


    String title;
    float amount;
    boolean isMonthly;
    UserSingleton theStuff = UserSingleton.Instance();
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Pop", "onCreate start");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_layout);

        saveButton = findViewById(R.id.buttonSave);
        saveButton.setEnabled(false);

        Bundle bundle = getIntent().getExtras();
        Log.d("Pop bundle size", Integer.toString(bundle.size()));

        isIncome = bundle.getBoolean("income");
        arrayIndex = bundle.getInt("index", -1);

        Log.d("Pop", "got bundle");
        BudgetCategory editMe = null;

        if (arrayIndex > -1){
            if (isIncome){
                editMe = theStuff.incomeCategories.get(arrayIndex);
            }
            else {
                editMe = theStuff.expenseCategories.get(arrayIndex);
            }
        }
        Log.d("Pop", "got item from singleton");

        final EditText titleName = findViewById(R.id.titleEdit);
        if (!(editMe==null)) {
            titleName.setText(editMe.getCategoryName());
        }

        final EditText amountText = findViewById(R.id.amountEdit);
        if (!(editMe==null)) {
            amountText.setText(String.valueOf(editMe.getTotalAmount()));
            if (editMe.isMonthly()){
                RadioButton monthly = (RadioButton) findViewById(R.id.radio_monthly);
                monthly.setChecked(true);
                saveButton.setEnabled(true);
            }
            else{
                RadioButton one_time = (RadioButton) findViewById(R.id.radio_onetime);
                one_time.setChecked(true);
                saveButton.setEnabled(true);
            }
        }
        else {
            amountText.setText(String.valueOf((float) 0.00));
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(width*.7));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                title = titleName.getText().toString();
                amount = Float.parseFloat(amountText.getText().toString());
                BudgetCategory iHateJava;
                //if we already have a thing we put that back
                if(arrayIndex > -1){
                    if(isIncome){
                        iHateJava = theStuff.incomeCategories.get(arrayIndex);
                        iHateJava.setCategoryName(title);
                        iHateJava.setMonthly(isMonthly);
                        if(isMonthly){
                            iHateJava.setMonthlyAmount(amount);
                        }
                        else{
                            iHateJava.setTotalAmount(amount);
                        }

                    }
                    else{
                        iHateJava = theStuff.expenseCategories.get(arrayIndex);
                        iHateJava.setCategoryName(title);
                        iHateJava.setMonthly(isMonthly);
                        if(isMonthly){
                            iHateJava.setMonthlyAmount(amount);
                        }
                        else{
                            iHateJava.setTotalAmount(amount);
                        }
                    }
                }
                else{
                    //create budget item and send it to the singleton
                    iHateJava = new BudgetCategory(title, isMonthly, amount);
                    if(isIncome){
                        theStuff.incomeCategories.add(iHateJava);
                    }
                    else{
                        theStuff.expenseCategories.add(iHateJava);
                    }

                }

                finish();
            }
        });

        Button cancelButton = findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                finish();
            }
        });
    }
    public void onRadioButtonClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_onetime:
                if (checked)
                    isMonthly = false;
                    saveButton.setEnabled(true);
                    break;
            case R.id.radio_monthly:
                if (checked)
                    isMonthly = true;
                    saveButton.setEnabled(true);
                    break;
        }
    }
}
