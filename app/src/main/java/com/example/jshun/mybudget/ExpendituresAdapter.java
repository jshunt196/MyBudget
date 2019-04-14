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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExpendituresAdapter extends RecyclerView.Adapter<ExpendituresAdapter.ViewHolder> {
    private static final String TAG = "ExpensesAdapter";

    private Context context;
    UserSingleton mySingleton = UserSingleton.Instance();
    ArrayList<BudgetCategory> listItems;

    public ExpendituresAdapter(Context myContext, ArrayList<BudgetCategory> listItems) {
        this.context = myContext;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenditures_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Log.d(TAG, "OnBindViewHolder: called");
        ArrayList<String> catagoryName = new ArrayList<>();
        ArrayList<Float> catagoryAmount = new ArrayList<>();
        for (BudgetCategory j : mySingleton.expenseCategories) {
            catagoryName.add(j.getCategoryName());
            catagoryAmount.add(j.getTotalAmount());
//            System.out.println(j.getCategory());
        }

        viewHolder.expName.setText(listItems.get(i).getCategoryName());
        viewHolder.expSpend.setText("$0");
        viewHolder.expAllocate.setText(String.valueOf(listItems.get(i).getTotalAmount()));
        viewHolder.expRemaining.setText("$200");
//        final String rowNames = catagoryNames.get(i);

        viewHolder.myRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, TransactionHistory.class);
                Bundle bundle = new Bundle();
                bundle.putString("ROW", viewHolder.expName.getText().toString());
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mySingleton.expenseCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView expName;
        TextView expSpend;
        TextView expAllocate;
        TextView expRemaining;
        LinearLayout myRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            expName = itemView.findViewById(R.id.expenditureName);
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