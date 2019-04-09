package com.example.jshun.mybudget;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeleteConfirm extends Activity{


    boolean isOnce;
    int arrayIndex;


    String title;
    UserSingleton theStuff = UserSingleton.Instance();
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DeleteConfirm", "onCreate start");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.delete_confirm_layout);

        Bundle bundle = getIntent().getExtras();
        Log.d("Pop bundle size", Integer.toString(bundle.size()));

        isOnce = bundle.getBoolean("once");
        arrayIndex = bundle.getInt("index", -1);

        Log.d("DeleteConfirm", "got bundle");
        BudgetItem editMe = null;

        if (arrayIndex > -1){
            if (isOnce){
                editMe = theStuff.incomeCategories.get(arrayIndex);
            }
            else{
                editMe = theStuff.expenseCategories.get(arrayIndex);
            }
        }
        Log.d("DeleteConfirm", "got item from singleton");

        final TextView titleName = findViewById(R.id.text_description);
        if (!(editMe==null)) {
            titleName.setText("Are you sure you wish to delete " + editMe.getCategory());
        }



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(width*.7));

        deleteButton = findViewById(R.id.buttonConfirmDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //if we already have a thing we put that back
                if(arrayIndex > -1){
                    if(isOnce){
                        theStuff.incomeCategories.remove(arrayIndex);
                    }
                    else{
                        theStuff.expenseCategories.remove(arrayIndex);
                    }
                }
                else{
                    //something has gone horribly wrong and someone should be notified.
                }

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
}
