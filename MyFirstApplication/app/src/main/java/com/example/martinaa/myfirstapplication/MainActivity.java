package com.example.martinaa.myfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rview;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public List<ListItem> fill_with_data() {

        List<ListItem> list = new ArrayList<>();

        list.add(new ListItem("Following", "Batman"));
        list.add(new ListItem("X-Men: Apocalypse", "X-Men"));
        list.add(new ListItem("Captain America: Civil War", "A feud"));
        list.add(new ListItem("Kung Fu Panda 3", "After reuniting"));
        list.add(new ListItem("Warcraft", "Fleeing"));
        list.add(new ListItem("Alice in Wonderland", "Alice"));

        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rview = (RecyclerView) findViewById(R.id.recycler);

        List<ListItem> list = fill_with_data();

        //create and set Layout manager
        manager = new LinearLayoutManager(this);
        rview.setLayoutManager(manager);

        //specify an adapter
        adapter = new ListAdapter (list, getApplication());
        rview.setAdapter(adapter);

    }



}
