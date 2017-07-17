package com.example.martinaa.cleanapp.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.martinaa.cleanapp.R;

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
    }
}