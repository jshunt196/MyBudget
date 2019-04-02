package com.example.jshun.mybudget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {
    private static final String TAG = "ExpensesAdapter";

    private ArrayList<String> catagoryNames = new ArrayList<>();
    private Context myContext;

    public IncomeAdapter(Context myContext, ArrayList<String> catagoryNames) {
        this.catagoryNames = catagoryNames;
        this.myContext = myContext;
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
    }

    @Override
    public int getItemCount() {
        return catagoryNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView catagoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catagoryName = itemView.findViewById(R.id.catName);
        }
    }
}
