package com.example.martinaa.cleanapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.example.martinaa.cleanapp.data.TaskDBAdapter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by martinaa on 25/07/2017.
 */

public class ToBeDoneActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView my_rview;
    private RecyclerView.Adapter my_adapter;
    private RecyclerView.LayoutManager my_manager;
    ArrayList<ListTasks> list = new ArrayList<>();

    private DrawerLayout my_layout;
    private ActionBarDrawerToggle my_toggle;
    Toolbar my_toolbar = null;
    NavigationView nv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobedone);

        my_toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show and hide the navigation menu on the left side
        my_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        my_toggle = new ActionBarDrawerToggle(this, my_layout, R.string.open, R.string.close);
        my_layout.addDrawerListener(my_toggle);
        my_toggle.syncState();

        //create Recyclerview and fill with data
        my_rview = (RecyclerView) findViewById(R.id.recycler1);

        //style the border of each item in Recyclerview
        DividerItemDecoration decoration = new DividerItemDecoration(ToBeDoneActivity.this, DividerItemDecoration.VERTICAL);
        my_rview.addItemDecoration(decoration);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider));

        my_manager = new LinearLayoutManager(this);
        my_rview.setLayoutManager(my_manager);
        my_adapter = new TaskAdapter(this, list);
        my_rview.setAdapter(my_adapter);
        my_adapter.notifyDataSetChanged();

        retrieve();

        //Method for enabling clicking on navigation view's options, from activity_main.xml
        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(my_toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Handler for different activities which should start when choosing some from the navigation drawer.
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_dashboard){
            //Do nothing for now
        }
        else if(id == R.id.nav_todo){
            Intent todointent = new Intent(ToBeDoneActivity.this, ToBeDoneActivity.class);
            startActivity(todointent);
        }
        else if(id == R.id.nav_list){
            Intent listintent = new Intent(ToBeDoneActivity.this, TasksActivity.class);
            startActivity(listintent);
        }
        else if(id == R.id.nav_achieve){
            //Do nothing for now
        }
        else if(id == R.id.nav_collect){
            //Do nothing for now
        }
        else if(id == R.id.nav_setup){
            //Do nothing for now
        }
        my_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void retrieve(){
        TaskDBAdapter db = new TaskDBAdapter(this);
        db.open();

        Cursor c = db.getListContents();
        //add the data using ListTasks's constructor
        while(c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String timetodo = c.getString(2);

            //ListTasks is a predefined java class in our project
            ListTasks l = new ListTasks(id, name, timetodo);
            l.layout_type = 2;
            list.add(l);
        }

        Collections.sort(list, ListTasks.BY_DATE);
        //if the user wrote data, send it to the adapter
        if(list.size()>0){
            my_rview.setAdapter(my_adapter);
        }
        db.close();
    }

}
