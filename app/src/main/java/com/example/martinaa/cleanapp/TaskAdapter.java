package com.example.martinaa.cleanapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by martinaa on 17/07/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<ListTasks> list;
    Context context;

    final int VIEW_1 = 1;
    final int VIEW_2 = 2;

    public TaskAdapter(Context context, ArrayList<ListTasks> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == VIEW_2){
            itemView = inflater.inflate(R.layout.todo_task, parent, false);
            holder = new TodoHolder(itemView);
        } else{
            itemView = inflater.inflate(R.layout.single_task, parent, false);
            holder = new TaskHolder(itemView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder gholder, final int position) {
        final ListTasks l = list.get(position);
        if(gholder instanceof TaskHolder){
            TaskHolder holder1 = (TaskHolder) gholder;
            holder1.one.setText(l.bigtext);
            holder1.two.setText(l.smalltext);
            holder1.three.setText(l.time);
            holder1.swSelected.setChecked(l.isChecked);
            holder1.swSelected.setTag(l);

            holder1.swSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Switch sw = (Switch) v;
                    ListTasks id = (ListTasks) sw.getTag();
                    id.setSelected(sw.isChecked());
                    l.setSelected(sw.isChecked());

                    if(sw.isChecked()){
                        Toast.makeText(v.getContext(), "Do it soon!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(v.getContext(), "Doneeee! :)", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }
        else if (gholder instanceof TodoHolder){
            TodoHolder holder2 = (TodoHolder) gholder;
            holder2.one.setText(l.bigtext);
            holder2.two.setText(l.smalltext);
            holder2.btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Do nothing
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        ListTasks l = list.get(position);
        if (l.layout_type == 2) {
            return VIEW_2;
        } else {
            return VIEW_1;
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    //For List of tasks - layout
    public static class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView one;
        public TextView two;
        public TextView three;
        public Switch swSelected;
        ItemClickListener itemClickListener;

        public TaskHolder(View itemView) {
            super(itemView);
            one = (TextView) itemView.findViewById(R.id.bigtext);
            two = (TextView) itemView.findViewById(R.id.smallertext);
            three = (TextView) itemView.findViewById(R.id.timetext);
            swSelected = (Switch) itemView.findViewById(R.id.switch_btn);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener = ic;
        }
    }

    //For To do tasks - layout
    public static class TodoHolder extends RecyclerView.ViewHolder {
        public TextView one;
        public TextView two;
        public Button btn;

        public TodoHolder(View itemView) {
            super(itemView);
            one = (TextView) itemView.findViewById(R.id.bigtext);
            two = (TextView) itemView.findViewById(R.id.smallertext);
            btn = (Button) itemView.findViewById(R.id.btn);
        }
    }

}
