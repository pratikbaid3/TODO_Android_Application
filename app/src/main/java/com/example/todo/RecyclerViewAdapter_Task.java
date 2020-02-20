package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter_Task extends RecyclerView.Adapter<RecyclerViewAdapter_Task.ViewHolder>
{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mTaskName = new ArrayList<>();
    private ArrayList<String> mTaskDesc=new ArrayList<>();
    private ArrayList<String> mTaskDate = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter_Task(Context context, ArrayList<String> txtTaskName, ArrayList<String> txtTaskDesc , ArrayList<String> txtTaskDate)
    {
        mTaskName = txtTaskName;
        mTaskDesc = txtTaskDesc;
        mTaskDate=txtTaskDate;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_task_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.txtTaskName.setText(mTaskName.get(position));
        holder.txtTaskDate.setText(mTaskDate.get(position));
        holder.txtTaskDesc.setText(mTaskDesc.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mTaskName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTaskName;
        TextView txtTaskDesc;
        TextView txtTaskDate;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            txtTaskName=itemView.findViewById(R.id.txtTaskName);
            txtTaskDesc=itemView.findViewById(R.id.txtTaskDescription);
            txtTaskDate=itemView.findViewById(R.id.txtTaskDescription);

        }
    }
}

