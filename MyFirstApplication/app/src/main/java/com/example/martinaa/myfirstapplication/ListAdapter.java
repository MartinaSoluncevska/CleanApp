package com.example.martinaa.myfirstapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Button;

import java.util.Collections;
import java.util.List;

/**
 * Created by martinaa on 05/07/2017.
 */

//Provide a binding from specific dataset the to view displayed within Recyclerview
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    List<ListItem> list = Collections.emptyList();
    Context context;

    public ListAdapter(List<ListItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //Called when RecyclerView needs a new Viewholder of the given type of item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        //Display items of the adapter using onBindView method
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //Display data in the specified position
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.one.setText(list.get(position).bigtext);
        holder.two.setText(list.get(position).smalltext);
        holder.btn.setOnClickListener(new View.OnClickListener(){
          @Override
            public void onClick(View view){
              Log.d("Test", "Button clicked");
          }
        });
        holder.cbx.setOnCheckedChangeListener(null);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //Reference to the view for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView one;
        public TextView two;
        public Button btn;
        public CheckBox cbx;

        public MyViewHolder(View itemView) {

            super(itemView);
            one = (TextView) itemView.findViewById(R.id.bigtext);
            two = (TextView) itemView.findViewById(R.id.smallertext);
            btn = (Button) itemView.findViewById(R.id.itemButton);
            cbx = (CheckBox) itemView.findViewById(R.id.itemCheckbox);
        }
    }
}
