package com.example.martinaa.cleanapp.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.martinaa.cleanapp.R;
import com.example.martinaa.cleanapp.TasksActivity;

/**
 * Created by martinaa on 17/07/2017.
 */

public class FourthActivity extends Activity{

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_question);
        addListenerGoToTask();
    }

    public void addListenerGoToTask(){
        Button btn = (Button) findViewById(R.id.btncontinue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), TasksActivity.class);
                startActivity(intent1);
            }
        });
    }
}
