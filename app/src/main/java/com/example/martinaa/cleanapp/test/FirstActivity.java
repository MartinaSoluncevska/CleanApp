package com.example.martinaa.cleanapp.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.martinaa.cleanapp.R;
import com.example.martinaa.cleanapp.TasksActivity;

/**
 * Created by martinaa on 17/07/2017.
 */

public class FirstActivity extends Activity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        addListenerButton();
    }

    public void addListenerButton(){

        final Context context = this;

        Button button = (Button) findViewById(R.id.btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SecondActivity.class);
                startActivity(i);
            }
        });

        Button button2 = (Button) findViewById(R.id.btnSkip);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TasksActivity.class);
                startActivity(intent);
            }
        });
    }

}