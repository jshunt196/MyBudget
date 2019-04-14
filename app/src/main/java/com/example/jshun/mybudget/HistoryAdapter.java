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

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private String title;

    public HistoryAdapter(Context myContext) {
        context = myContext;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_adapter, parent, false);
        HistoryAdapter.ViewHolder holder = new HistoryAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder...setText(listItems.get(pos).getCategory() + ": $");
//        viewHolder.itemAmount.setText(String.valueOf(listItems.get(pos).getAmount()) + " per semester");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tranTitle;
        TextView tranAmount;
        TextView tranDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tranTitle = itemView.findViewById(R.id.transactionTitle);
            tranAmount = itemView.findViewById(R.id.transactionAmount);
            tranDate = itemView.findViewById(R.id.transactionDate);
        }
    }
}