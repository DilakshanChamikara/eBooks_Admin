package com.example.ebooks_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;


public class AdminAddDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button cnl, AddBtn, ClearBtn;

    //spinner arrays
    String[] bookCategory = {"",
            "Action and Adventure",
            "Anthology",
            "Biography/Autobiography",
            "Classic",
            "Comic and Graphic Novel",
            "Crime and Detective",
            "Drama",
            "Essay",
            "Fable",
            "Fairy Tale",
            "Fan-Fiction",
            "Fantasy",
            "Historical Fiction",
            "Horror",
            "Humor",
            "Legend",
            "Magical Realism",
            "Memoir",
            "Mystery",
            "Mythology",
            "Narrative Nonfiction",
            "Periodicals",
            "Poetry",
            "Realistic Fiction",
            "Reference Books",
            "Romance",
            "Satire",
            "Science Fiction (Sci-Fi)",
            "Self-help Book",
            "Short Story",
            "Speech",
            "Suspense/Thriller"
    };

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

        //category spinner
        Spinner spinner = (Spinner) findViewById(R.id.category);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter ArrCate = new ArrayAdapter(this,android.R.layout.simple_spinner_item, bookCategory);
        ArrCate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ArrCate);

        //language spinner
        Spinner spinner1 = (Spinner) findViewById(R.id.lang);
        spinner1.setOnItemSelectedListener(this);
        ArrayAdapter ArrLang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, language);
        ArrLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(ArrLang);
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
