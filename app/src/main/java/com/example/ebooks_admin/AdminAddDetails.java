package com.example.ebooks_admin;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;


public class AdminAddDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button cnl, AddBtn, ClearBtn;
    private EditText ISBN, TITLE, AUTHER, SIZE, INTRO, RPrice, FPrice;
    private Spinner CATEGORY, LANG;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private ImageView mImageView;
    public Uri mImageUri;
    private StorageTask addTask;

    private StorageReference StorageRef;
    private DatabaseReference DbRefAdmin;

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
        StorageRef = FirebaseStorage.getInstance().getReference("books");
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
//                if(addTask != null && addTask.isInProgress()){
//                    Toast.makeText(AdminAddDetails.this, "Task in progress", Toast.LENGTH_LONG).show();
//                }else{
//                    addFile();
//                }
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
//        Spinner spinner1 = (Spinner) findViewById(R.id.lang);
//        spinner1.setOnItemSelectedListener(this);
//        ArrayAdapter ArrLang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, language);
//        ArrLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner1.setAdapter(ArrLang);


        //background Animation
        constraintLayout = (ConstraintLayout) findViewById(R.id.addDetailsLayout);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();


        //image choose button
        mImageView = (ImageView)findViewById(R.id.chooseImage);

        mButtonChooseImage = (Button) findViewById(R.id.choose);
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

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


    //image choose button
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();

//            Picasso.with(this).load(mImageUri).into(mImageUri);
            mImageView.setImageURI(mImageUri);
        }
    }


    //get file extension
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
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

       // final String image = IMAGE.getText().toString().trim();


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

    private void addFile(){

        StorageReference fileReference = StorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));

        fileReference.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(AdminAddDetails.this, "Image upload successfull", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

//        addTask = fileReference.putFile(mImageUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(AdminAddDetails.this, "Image upload successfull", Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(AdminAddDetails.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });

    }
}
