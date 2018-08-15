package com.example.attendance.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendance.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity  implements View.OnClickListener{

    public static int screenHeight;
    public static int screenWidth;

    Button login;
    EditText email,password;
    TextView signUp;

    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Display display = getWindowManager().getDefaultDisplay();
        screenHeight = display.getHeight();
        screenWidth = display.getWidth();

        login=findViewById(R.id.login_button_login);
        email=findViewById(R.id.editText_login_id);
        password=findViewById(R.id.editText_login_password);
        signUp=findViewById(R.id.signUp);

        login.setOnClickListener(this);
        signUp.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));
        }
    }

    @Override
    public void onClick(View v) {
        if(v==login){
            loginAccout();
        }
        if(v==signUp){
            startActivity(new Intent(this,SignUp.class));
        }


    }

    private void loginAccout() {
        String mail=email.getText().toString().trim();
        String Password=password.getText().toString().trim();
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this,"Enter your gmail!",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Enter your Password",Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(mail,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }
                    }
                });
    }
}
