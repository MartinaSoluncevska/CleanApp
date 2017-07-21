package com.example.martinaa.cleanapp;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by martinaa on 21/07/2017.
 */

public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
