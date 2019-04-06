package com.example.jshun.mybudget;

import android.app.Activity;
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
    private Boolean once;

    Context mContext;

    public ExpensesAdapter(Context myContext, ArrayList<BudgetItem> listItems, boolean once) {
        this.listItems = listItems;
        this.context = myContext;
        this.once = once;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {
        Log.d(TAG, "OnBindViewHolder: called");

        viewHolder.itemTitle.setText(listItems.get(pos).getCategory() + ": $");
        viewHolder.itemAmount.setText(String.valueOf(listItems.get(pos).getAmount()) + " per semester");
        final String rowNames = listItems.get(pos).getCategory();

        viewHolder.myRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Pop.class);
                Bundle bundle = new Bundle();
                bundle.putString("ROW", rowNames);
                bundle.putBoolean("once", once);
                bundle.putInt("index", pos);
                i.putExtras(bundle);
                ((Activity)mContext).startActivityForResult(i, 0);
                //startActivityForResult(i, 0);
                //context.startActivity(i);
            }
        });

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent i = new Intent(context, Pop.class);
                Bundle bundle = new Bundle();
                bundle.putString("ROW", rowNames);
                bundle.putBoolean("once", once);
                bundle.putInt("index", pos);
                i.putExtras(bundle);
                ((Activity)mContext).startActivityForResult(i, 0);
                //context.startActivity(i);
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
