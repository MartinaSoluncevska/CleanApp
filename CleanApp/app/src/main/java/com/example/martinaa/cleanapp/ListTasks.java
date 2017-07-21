package com.example.martinaa.cleanapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by martinaa on 17/07/2017.
 */

public class ListTasks implements Serializable{
    public int id;
    public String bigtext;
    public String smalltext;
    public String time;
    public boolean isChecked;


    public ListTasks(int id, String bigtext, String smalltext, String time) {
        this.id=id;
        this.bigtext = bigtext;
        this.smalltext = smalltext;
        this.time = time;
    }

    public void setSelected(boolean selected) {
        this.isChecked = selected;
    }
}
