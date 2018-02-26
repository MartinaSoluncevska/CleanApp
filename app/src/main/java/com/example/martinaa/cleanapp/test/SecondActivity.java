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

public class SecondActivity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_question);
        addListenerOneButton();
    }

    public void addListenerOneButton(){
        final Context context = this;
        Button button = (Button) findViewById(R.id.btnyes);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

}
