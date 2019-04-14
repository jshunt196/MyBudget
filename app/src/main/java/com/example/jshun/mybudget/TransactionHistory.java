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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends AppCompatActivity {
    UserSingleton theStuff = UserSingleton.Instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_tranhist);

        /*RecyclerView recyclerExpenditures = findViewById(R.id.expRecycler);
        recyclerExpenditures.setAdapter(new ExpendituresAdapter(this, expenseCategories));
        recyclerExpenditures.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerExpenditures.addItemDecoration(itemDecoration);*/

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
    }
}