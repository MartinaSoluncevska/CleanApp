package com.example.martinaa.cleanapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by martinaa on 17/07/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{

    ArrayList<ListTasks> list;
    Context context;

    public TaskAdapter(Context context, ArrayList<ListTasks> list) {
        this.context = context;
        this.list = list;
    }

    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_TODO = 1;

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task, parent, false);
        TaskHolder holder = new TaskHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, final int position) {
        holder.one.setText(list.get(position).bigtext);
        holder.two.setText(list.get(position).smalltext);
        holder.three.setText(list.get(position).time);
        holder.swSelected.setChecked(list.get(position).isChecked);
        holder.swSelected.setTag(list.get(position));

        holder.swSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch sw = (Switch) v;
                ListTasks id = (ListTasks) sw.getTag();

                id.setSelected(sw.isChecked());
                list.get(position).setSelected(sw.isChecked());

                if(sw.isChecked()){
                    Toast.makeText(v.getContext(), "Do it soon!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(v.getContext(), "Doneeee! :)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
