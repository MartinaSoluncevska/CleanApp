package com.example.martinaa.cleanapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by martinaa on 17/07/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder>{
    List<ListTasks> list = Collections.emptyList();
    Context context;

    public TaskAdapter(List<ListTasks> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.MyViewHolder holder, final int position) {
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView one;
        public TextView two;
        public TextView three;
        public Switch swSelected;

        public MyViewHolder(View itemView) {
            super(itemView);
            one = (TextView) itemView.findViewById(R.id.bigtext);
            two = (TextView) itemView.findViewById(R.id.smallertext);
            three = (TextView) itemView.findViewById(R.id.timetext);
            swSelected = (Switch) itemView.findViewById(R.id.switch_btn);
        }
    }
}
