package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    public static int screenHeight;
    public static int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        screenHeight = display.getHeight();
        screenWidth = display.getWidth();
        Log.i("TAG", "screenHeight = " + screenHeight);
        Log.i("TAG", "screenWidth  = " + screenWidth);
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
