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

    private ArrayList<BudgetItem> listItems;
    private Context context;

    public ExpensesAdapter(Context myContext, ArrayList<BudgetItem> listItems) {
        this.listItems = listItems;
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

        viewHolder.itemTitle.setText(listItems.get(i).getCategory() + ": $");
        viewHolder.itemAmount.setText(String.valueOf(listItems.get(i).getAmount()));
        final String rowNames = listItems.get(i).getCategory();

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

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
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
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle;
        TextView itemAmount;
        Button editButton;
        LinearLayout myRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle = itemView.findViewById(R.id.catName);
            itemAmount = itemView.findViewById(R.id.catAmount);
            editButton = itemView.findViewById(R.id.buttonEdit);
            myRow = itemView.findViewById(R.id.expenseRow);
        }
    }
}
