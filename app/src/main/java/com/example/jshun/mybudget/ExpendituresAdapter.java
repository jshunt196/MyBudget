package com.example.jshun.mybudget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpendituresAdapter extends RecyclerView.Adapter<ExpendituresAdapter.ViewHolder> {
    private static final String TAG = "ExpensesAdapter";

    private ArrayList<String> catagoryNames = new ArrayList<>();
    private Context context;

    public ExpendituresAdapter(Context myContext, ArrayList<String> myCatagoryNames) {
        this.context = myContext;
        this.catagoryNames = myCatagoryNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenditures_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "OnBindViewHolder: called");

        viewHolder.expSpend.setText(catagoryNames.get(i));
        viewHolder.expAllocate.setText("$200");
        viewHolder.expRemaining.setText("$200");
        final String rowNames = catagoryNames.get(i);

        viewHolder.myRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(context, Pop.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("ROW", rowNames);
//                i.putExtras(bundle);
//                context.startActivity(i);
                Toast toast = Toast.makeText(context, "You're so cool!", Toast.LENGTH_LONG);
                toast.show();
                context.startActivity(new Intent(context, AddTransactionFrag.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return catagoryNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView expSpend;
        TextView expAllocate;
        TextView expRemaining;
        LinearLayout myRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            expSpend = itemView.findViewById(R.id.expSpent);
            expAllocate = itemView.findViewById(R.id.expAllocate);
            expRemaining = itemView.findViewById(R.id.expRemain);
            myRow = itemView.findViewById(R.id.expRow);
        }
    }
}


/*
package com.example.jshun.mybudget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {
    private static final String TAG = "ExpensesAdapter";

    private ArrayList<String> catagoryNames = new ArrayList<>();
    private Context context;

    public ExpensesAdapter(Context myContext, ArrayList<String> catagoryNames) {
        this.catagoryNames = catagoryNames;
        this.context = myContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "OnBindViewHolder: called");

        viewHolder.catagoryName.setText(catagoryNames.get(i) + ": $");
        viewHolder.catagoryAmount.setText("0.00");
        final String rowNames = catagoryNames.get(i);

        viewHolder.myRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Pop.class);
                Bundle bundle = new Bundle();
                bundle.putString("ROW", rowNames);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });

        viewHolder.catagoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent i = new Intent(context, Pop.class);
                Bundle bundle = new Bundle();
                bundle.putString("ROW", rowNames);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return catagoryNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView catagoryName;
        TextView catagoryAmount;
        Button catagoryAdd;
        LinearLayout myRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catagoryName = itemView.findViewById(R.id.catName);
            catagoryAmount = itemView.findViewById(R.id.catAmount);
            catagoryAdd = itemView.findViewById(R.id.addCat);
            myRow = itemView.findViewById(R.id.expenseRow);
        }
    }
}

 */