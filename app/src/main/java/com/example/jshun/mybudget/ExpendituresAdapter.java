package com.example.jshun.mybudget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExpendituresAdapter extends RecyclerView.Adapter<ExpendituresAdapter.ViewHolder> {
    private static final String TAG = "ExpensesAdapter";

    private Context context;
    UserSingleton mySingleton = UserSingleton.Instance();
    ArrayList<BudgetCategory> listItems;
    private boolean isIncome;

    public ExpendituresAdapter(Context myContext, boolean isIncome) {
        this.context = myContext;
        this.isIncome = isIncome;
        if (isIncome){
            this.listItems = mySingleton.getIncomeCategories();
        }
        else {
            this.listItems = mySingleton.getExpenseCategories();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenditures_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "OnBindViewHolder: called");
        ArrayList<String> catagoryName = new ArrayList<>();
        ArrayList<Float> catagoryAmount = new ArrayList<>();
        for (BudgetCategory j : mySingleton.expenseCategories) {
            catagoryName.add(j.getCategoryName());
            catagoryAmount.add(j.getTotalAmount());
//            System.out.println(j.getCategory());
        }

        final String expName = listItems.get(i).getCategoryName();
        final String stringSpent = "$"+String.format("%.2f", listItems.get(i).getTotalExpended());
        final int intSpent = Math.round(listItems.get(i).getTotalExpended());
        final String expAllocate = "$"+String.format("%.2f", listItems.get(i).getTotalAmount());
        final int intAllocate = Math.round(listItems.get(i).getTotalAmount());

        viewHolder.expName.setText(expName);
        viewHolder.expSpend.setText(stringSpent);
        viewHolder.expAllocate.setText(expAllocate);
        float remaining = listItems.get(i).getAmountRemaining();

        final String expRemaining = "$" + String.format("%.2f", remaining);
        if (remaining > 0) {
            viewHolder.expRemaining.setTextColor(Color.parseColor("#4CAF50")); ///////////////////////////////////////////// FIX ME!!
        }
        else if (remaining < 0) {
            viewHolder.expRemaining.setTextColor(Color.parseColor("#F44336"));
        }
        viewHolder.expRemaining.setText(expRemaining);
        float exp = listItems.get(i).getTotalExpended();
        float total = listItems.get(i).getTotalAmount();
        float progress = exp/total*100;
        int valueStuff = Math.round(progress);
        viewHolder.expProgress.setProgress(valueStuff);
//        final String rowNames = catagoryNames.get(i);

        viewHolder.myRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TransactionHistory.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("income", isIncome);
                bundle.putInt("index", i);
                bundle.putString("RowName", expName);
                bundle.putInt("Spent", intSpent);
                bundle.putInt("Budget", intAllocate);
                intent.putExtras(bundle);
                context.startActivity(intent);
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
        ProgressBar expProgress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            expName = itemView.findViewById(R.id.expenditureName);
            expSpend = itemView.findViewById(R.id.expSpent);
            expAllocate = itemView.findViewById(R.id.expAllocate);
            expRemaining = itemView.findViewById(R.id.expRemain);
            myRow = itemView.findViewById(R.id.expRow);
            expProgress = itemView.findViewById(R.id.expProgress);
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