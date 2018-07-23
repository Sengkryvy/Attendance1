package com.example.attendance.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import com.example.attendance.R;

public class Main extends AppCompatActivity {

    public static int screenHeight;
    public static int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Display display = getWindowManager().getDefaultDisplay();
        screenHeight = display.getHeight();
        screenWidth = display.getWidth();

    }

    public void OnclickLogin(View view){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

}}
