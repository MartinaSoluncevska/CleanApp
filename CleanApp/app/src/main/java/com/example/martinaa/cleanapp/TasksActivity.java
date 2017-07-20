package com.example.martinaa.cleanapp;

/**
 * Created by martinaa on 13/07/2017.
 */
import android.content.Intent;
import android.database.DatabaseUtils;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.martinaa.cleanapp.data.TaskHelper;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity{
    private RecyclerView my_rview;
    private RecyclerView.Adapter my_adapter;
    private RecyclerView.LayoutManager my_manager;
    private TaskHelper my_helper;
    ArrayList<ListTasks> list = new ArrayList<>();

    private DrawerLayout my_layout;
    private ActionBarDrawerToggle my_toggle;
    private Toolbar my_toolbar;

    private static final int TASK_LOADER=0;

    private Switch my_switch;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_toolbar = (Toolbar) findViewById(R.id.nav_action);
        my_switch = (Switch) findViewById(R.id.switch_btn);
        setSupportActionBar(my_toolbar);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TasksActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        my_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        my_toggle = new ActionBarDrawerToggle(this, my_layout, R.string.open, R.string.close);
        my_layout.addDrawerListener(my_toggle);
        my_toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        my_rview = (RecyclerView) findViewById(R.id.recycler);

        my_manager = new LinearLayoutManager(this);
        my_rview.setLayoutManager(my_manager);

        my_adapter = new TaskAdapter(list, getApplication());
        my_rview.setAdapter(my_adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(my_toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
