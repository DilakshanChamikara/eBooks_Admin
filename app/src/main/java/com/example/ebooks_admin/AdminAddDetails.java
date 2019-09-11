package com.example.ebooks_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminAddDetails extends AppCompatActivity {

    private Button cnl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_details);

        //cancel button
        cnl = (Button) findViewById(R.id.cancel);
        cnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminMenu();
            }
        });
    }

    //cancel button
    public void openAdminMenu() {
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
    }
}
