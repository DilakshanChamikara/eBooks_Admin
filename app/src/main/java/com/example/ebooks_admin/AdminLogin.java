package com.example.ebooks_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLogin extends AppCompatActivity {

    private Button btn1, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        //Login button
        btn1 = (Button) findViewById(R.id.buttonLog);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
            }
        });

        //Cancel button
        cancel = (Button) findViewById(R.id.buttonCnl);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeLogin();
            }
        });
    }

    //login method
    public void openMenu(){
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
    }

    //cancel method
    public void closeLogin(){
        finishAffinity();
    }
}
