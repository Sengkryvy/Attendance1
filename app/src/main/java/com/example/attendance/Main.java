package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_about(View view){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void onClick_login(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


}
