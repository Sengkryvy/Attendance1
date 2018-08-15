package com.example.attendance.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    Button register;
    EditText password,mail;
    TextView signIn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        register=findViewById(R.id.registerBtn);
        password=findViewById(R.id.pass_register);
        mail=findViewById(R.id.register_mail);
        signIn=findViewById(R.id.signIn);

        register.setOnClickListener(this);
        signIn.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));
        }

    }

    @Override
    public void onClick(View v) {
        if(v==register){
            register_name();

        }
        if(v==signIn){
            startActivity(new Intent(this,Main.class));
        }

    }

    private void register_name() {
        String gmail=mail.getText().toString().trim();
        String Pass=password.getText().toString().trim();
        if(TextUtils.isEmpty(gmail)){
            Toast.makeText(this,"Enter your gmail!",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Pass)){
            Toast.makeText(this,"Enter your Password",Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(gmail,Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        } else {
                            Toast.makeText(SignUp.this, "Can not create", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
