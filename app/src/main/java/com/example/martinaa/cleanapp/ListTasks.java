package com.example.martinaa.cleanapp;

import android.widget.Button;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Created by martinaa on 17/07/2017.
 */

public class ListTasks implements Serializable{
    public int id;
    public String bigtext;
    public String smalltext;
    public String time;
    public Button btn;
    public boolean isChecked;
    public int layout_type;

    public ListTasks(int id, String bigtext, String smalltext, String time, String information) {
        this.id=id;
        this.bigtext = bigtext;
        this.smalltext = smalltext;
        this.time = time;
    }

    public ListTasks(int id, String bigtext, String smalltext) {
        this.id=id;
        this.bigtext = bigtext;
        this.smalltext = smalltext;
    }

    public void setSelected(boolean selected) {
        this.isChecked = selected;
    }
}
