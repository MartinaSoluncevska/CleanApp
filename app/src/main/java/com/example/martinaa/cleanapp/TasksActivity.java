package com.example.martinaa.cleanapp;

/**
 * Created by martinaa on 13/07/2017.
 */
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.example.martinaa.cleanapp.data.TaskDBAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TasksActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    EditText title;
    Spinner info, something;

    private RecyclerView my_rview;
    private RecyclerView.Adapter my_adapter;
    private RecyclerView.Adapter category_adapter;
    private RecyclerView.LayoutManager my_manager;
    ArrayList<ListTasks> list = new ArrayList<>();
    ArrayList<ListTasks> filteredList = new ArrayList<ListTasks>();

    private DrawerLayout my_layout;
    private ActionBarDrawerToggle my_toggle;
    Toolbar my_toolbar = null;
    NavigationView nv = null;
    Switch my_switch;

    Spinner spinnerCategory;
    Spinner spinnerDate;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //main toolbar of CleanApp
        my_toolbar = (Toolbar) findViewById(R.id.nav_action);
        my_switch = (Switch) findViewById(R.id.switch_btn);
        setSupportActionBar(my_toolbar);

        // Setup FAB to open showDialog
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show and hide the navigation menu on the left side
        my_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        my_toggle = new ActionBarDrawerToggle(this, my_layout, R.string.open, R.string.close);
        my_layout.addDrawerListener(my_toggle);
        my_toggle.syncState();

        //create Recyclerview and fill with data
        my_rview = (RecyclerView) findViewById(R.id.recycler);
        my_manager = new LinearLayoutManager(this);
        my_rview.setLayoutManager(my_manager);

        //style the border of each item in Recyclerview
        DividerItemDecoration decoration = new DividerItemDecoration(TasksActivity.this, DividerItemDecoration.VERTICAL);
        my_rview.addItemDecoration(decoration);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider));

        my_adapter = new TaskAdapter(this, list);
        my_rview.setAdapter(my_adapter);
        retrieve();

        //sort
        spinnerCategory = (Spinner) findViewById(R.id.spinner1);
        spinnerDate = (Spinner) findViewById(R.id.spinner2);
        category_adapter = new TaskAdapter(TasksActivity.this, filteredList);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        my_rview.setAdapter(my_adapter);
                        break;
                    case 1:
                        filteredList.clear();
                        for(ListTasks l : list){
                            if(l.bigtext.equals("wash dishes")){
                                filteredList.add(l);
                            }
                        }
                        my_rview.setAdapter(category_adapter);
                        break;
                    case 2:
                        filteredList.clear();
                        for(ListTasks l1 : list){
                            if(l1.bigtext.equals("vacuum") || l1.bigtext.equals("v")){
                                filteredList.add(l1);
                            }
                        }
                        my_rview.setAdapter(category_adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

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
            Intent todointent = new Intent(TasksActivity.this, ToBeDoneActivity.class);
            startActivity(todointent);
        }
        else if(id == R.id.nav_list){
            Intent listintent = new Intent(TasksActivity.this, TasksActivity.class);
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

    //Dialog for adding new tasks. which opens when click on FAB button
    private void showDialog(){
        final Dialog d = new Dialog(this);
        //No title
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set layout
        d.setContentView(R.layout.activity_edit);

        title = (EditText) d.findViewById(R.id.edit_name);
        something = (Spinner) d.findViewById(R.id.edit_time);
        info = (Spinner) d.findViewById(R.id.edit_left);

        //adapter for the first spinner object
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(TasksActivity.this, R.array.time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        something.setAdapter(adapter);
        something.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String timeString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //adapter for the second spinner object
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(TasksActivity.this, R.array.left, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        info.setAdapter(adapter1);
        info.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String leftString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //add button's handler when click, which fills database with the user input
        final Button addBtn = (Button) d.findViewById(R.id.btnadd);
        Button cancelBtn = (Button) d.findViewById(R.id.btncancel);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addData(title.getText().toString(), something.getSelectedItem().toString().trim(), info.getSelectedItem().toString().trim());
                d.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

        //show the dialog window
        d.show();
    }

    //method for adding data
    private void addData(String name, String timetodo, String timeleft) {
        TaskDBAdapter db = new TaskDBAdapter(this);
        db.open();
        list.clear();

        //variable to hold the user input
        long result = db.addData(name, timetodo, timeleft);
        if(result > 0){
            //Only for the text. Values from spinner objects are previously defined in strings.xml
            title.setText("");
            Toast.makeText(TasksActivity.this, "The task has been inserted!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(TasksActivity.this, "You have blank fields left!", Toast.LENGTH_SHORT).show();
        }
        db.close();

        //refresh the layout with the updated data
        retrieve();
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
            String timeleft = c.getString(3) + " left";

            //ListTasks is a predefined java class in our project
            ListTasks l = new ListTasks(id, name, timetodo, timeleft, null);
            l.layout_type = 1;
            list.add(l);
        }

        //if the user wrote data, send it to the adapter
        if(list.size()>0){
            my_rview.setAdapter(my_adapter);
        }
        db.close();
    }

}
