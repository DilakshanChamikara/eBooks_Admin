package com.example.ebooks_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminAddDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button cnl, AddBtn, ClearBtn;
    private EditText ISBN, TITLE, AUTHER, SIZE, INTRO, RPrice, FPrice;
    private Spinner CATEGORY, LANG;

    DatabaseReference DbRefAdmin;

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

        //db
        DbRefAdmin = FirebaseDatabase.getInstance().getReference("books");

        //find Ids
        ISBN = (EditText) findViewById(R.id.isbn);
        TITLE = (EditText) findViewById(R.id.title);
        AUTHER = (EditText) findViewById(R.id.auther);
        SIZE = (EditText) findViewById(R.id.size);
        INTRO = (EditText) findViewById(R.id.intro);
        RPrice = (EditText) findViewById(R.id.rPrice);
        FPrice = (EditText) findViewById(R.id.fPrice);

        CATEGORY = (Spinner) findViewById(R.id.category);
        LANG = (Spinner) findViewById(R.id.lang);


        //add button
        AddBtn = (Button) findViewById(R.id.addButton);
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecord();
            }
        });

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

    //add button
    private void addRecord(){
        String isbn = ISBN.getText().toString().trim();
        String title = TITLE.getText().toString().trim();
        String auther = AUTHER.getText().toString().trim();
        String size = SIZE.getText().toString().trim();
        String intro = INTRO.getText().toString().trim();
        String rPrice = RPrice.getText().toString().trim();
        String fPrice = FPrice.getText().toString().trim();

        String category = CATEGORY.getSelectedItem().toString();
        String lang = LANG.getSelectedItem().toString();

        if(TextUtils.isEmpty(isbn) || TextUtils.isEmpty(title) || TextUtils.isEmpty(auther) || TextUtils.isEmpty(size) || TextUtils.isEmpty(intro) ||
                TextUtils.isEmpty(rPrice) || TextUtils.isEmpty(fPrice) || TextUtils.isEmpty(category) || TextUtils.isEmpty(lang)){


            Toast.makeText(this, "Please fill the all fields.", Toast.LENGTH_LONG).show();

        }else {

            String id = DbRefAdmin.push().getKey();
            AddRecord books = new AddRecord(isbn, title, auther, size, intro, rPrice, fPrice, category, lang);
            DbRefAdmin.child(id).setValue(books);
            Toast.makeText(this, "Book is Successfully added.", Toast.LENGTH_LONG).show();
            clearForm();
        }
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
