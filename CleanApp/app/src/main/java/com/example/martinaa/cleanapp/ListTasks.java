package com.example.martinaa.cleanapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by martinaa on 17/07/2017.
 */

public class ListTasks implements Serializable{
    public String bigtext;
    public String smalltext;
    public String time;
    public boolean isChecked;


    public ListTasks(String bigtext, String smalltext, String time, boolean isChecked) {
        this.bigtext = bigtext;
        this.smalltext = smalltext;
        this.time = time;
        this.isChecked = isChecked;
    }

    public void setSelected(boolean selected) {
        this.isChecked = selected;
    }
}
