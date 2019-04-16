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
import java.util.Calendar;
import java.util.Date;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private String title;
    private ArrayList<transactionItem> listTransactions;

    public HistoryAdapter(Context myContext, ArrayList<transactionItem> transactions) {
        context = myContext;
        listTransactions = transactions;
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
        viewHolder.tranTitle.setText(listTransactions.get(i).getName());
        float mehFloat = listTransactions.get(i).getAmount();
        viewHolder.tranAmount.setText("$" + String.format("%.2f", mehFloat));

        int year = listTransactions.get(i).getDate().getYear();
        int month = listTransactions.get(i).getDate().getMonth();
        int day = listTransactions.get(i).getDate().getDate();

        String myDate = String.valueOf(month) + "/" + String.valueOf(day) + "/" + String.valueOf(year).substring(String.valueOf(year).length() - 2);
        viewHolder.tranDate.setText(myDate);
    }

    @Override
    public int getItemCount() {
        return listTransactions.size();
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