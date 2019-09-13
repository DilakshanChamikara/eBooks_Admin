package com.example.ebooks_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;


public class AdminAddDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button cnl, AddBtn, ClearBtn;

    //background Animation
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable ;

    //language spinner
    String[] language = {"",
            "English",
            "Sinhala",
            "Tamil"
    };

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

        //clear All button
        ClearBtn = (Button) findViewById(R.id.clear);
        ClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearForm();
            }
        });


        //language spinner
        Spinner spinner1 = (Spinner) findViewById(R.id.lang);
        spinner1.setOnItemSelectedListener(this);
        ArrayAdapter ArrLang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, language);
        ArrLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(ArrLang);


        //background Animation
        constraintLayout = (ConstraintLayout) findViewById(R.id.addDetailsLayout);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

    }


    //cancel button
    public void openAdminMenu() {
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
    }


    //clear All button
    private void clearForm() {
        startActivity(new Intent(this, AdminAddDetails.class));
        overridePendingTransition(0,0);
        this.finish();
    }


    //spinner methods
    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int pos, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
