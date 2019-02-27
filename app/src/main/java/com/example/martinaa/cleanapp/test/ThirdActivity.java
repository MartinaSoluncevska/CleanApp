package com.example.martinaa.cleanapp.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.martinaa.cleanapp.R;

/**
 * Created by martinaa on 17/07/2017.
 */

public class ThirdActivity extends Activity implements View.OnClickListener{

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_question);
        Button one, two, three, four, five;

        one = (Button) findViewById(R.id.btn1);
        one.setOnClickListener(this);
        two = (Button) findViewById(R.id.btn2);
        two.setOnClickListener(this);
        three = (Button) findViewById(R.id.btn3);
        three.setOnClickListener(this);
        four = (Button) findViewById(R.id.btn4);
        four.setOnClickListener(this);
        five = (Button) findViewById(R.id.btn5);
        five.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn1
                || view.getId() == R.id.btn2
                || view.getId() == R.id.btn3
                || view.getId() == R.id.btn4
                || view.getId() == R.id.btn5){

            Intent i = new Intent(getApplicationContext(), FourthActivity.class);
            startActivity(i);
        }
    }
}
