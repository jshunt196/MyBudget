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

    String title;
    Float amount;
    Boolean isExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_layout);

        Bundle bundle = getIntent().getExtras();
        String venName = bundle.getString("ROW");

        final EditText titleName = findViewById(R.id.titleEdit);
        if (!venName.equals(null)) {
            titleName.setText(venName);

        }

        final EditText amountText = findViewById(R.id.amountEdit);
        amountText.setText("0");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(width*.7));

        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                title = titleName.getText().toString();
                amount = Float.parseFloat(amountText.getText().toString());
                if(isExpense){
                    amount = amount * -1;
                }
                //create budget item and send it to the singleton
                Log.d("pop", "contents below");
                Log.d(title,amountText.getText().toString());
                finish();
            }
        });

        Button cancelButton =findViewById(R.id.buttonCancel);
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
            case R.id.radio_income:
                if (checked)
                    isExpense = false;
                    break;
            case R.id.radio_expense:
                if (checked)
                    isExpense = true;
                    break;
        }
    }
}
