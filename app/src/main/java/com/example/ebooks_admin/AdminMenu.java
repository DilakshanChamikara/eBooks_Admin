package com.example.ebooks_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMenu extends AppCompatActivity {

    private Button Add, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        //For Add Button
        Add = (Button) findViewById(R.id.sltadd);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDetails();
            }
        });

        //For Logout Button
        Logout = (Button) findViewById(R.id.sltlogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminPanel();
            }
        });
    }

    //add method
    public void openAddDetails(){
        Intent intent = new Intent(this, AdminAddDetails.class);
        startActivity(intent);
    }


    //logout method
    public void openAdminPanel(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
