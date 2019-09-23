package com.example.ebooks_admin;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdminUpdateDeleteDetails extends AppCompatActivity {

    private Button uUpdate, uDelete, uCancel;
    private EditText uISBN, uTITLE, uAUTHER, uSIZE, uINTRO, uRPrice, uFPrice, uIMAGE;
    private Spinner uCATEGORY, uLANG;
    private static final int PICK_IMAGE_REQUEST = 1;
    public Uri mImageUri;
    private ImageView mImageView;
    private Button mButtonChooseImage;

    private StorageReference StorageRef;
    private DatabaseReference DbRefAdmin;

    private String key;
    private String auther, bookNo, bookTitle, category, fullPrice, intro, lang, rentPrice, size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_delete_details);

        //db
        StorageRef = FirebaseStorage.getInstance().getReference("books");
        DbRefAdmin = FirebaseDatabase.getInstance().getReference("books");

        //get the data form intent
        key = getIntent().getStringExtra("key");
        auther = getIntent().getStringExtra("auther");
        bookNo = getIntent().getStringExtra("bookNo");
        bookTitle = getIntent().getStringExtra("bookTitle");
        category = getIntent().getStringExtra("category");
        fullPrice = getIntent().getStringExtra("fullPrice");
        intro = getIntent().getStringExtra("intro");
        lang = getIntent().getStringExtra("lang");
        rentPrice = getIntent().getStringExtra("rentPrice");
        size = getIntent().getStringExtra("size");


        //find Ids
        uISBN = (EditText) findViewById(R.id.isbn_u);
        uISBN.setText(bookNo);

        uTITLE = (EditText) findViewById(R.id.title_u);
        uTITLE.setText(bookTitle);

        uAUTHER = (EditText) findViewById(R.id.auther_u);
        uAUTHER.setText(auther);

        uSIZE = (EditText) findViewById(R.id.size_u);
        uSIZE.setText(size);

        uINTRO = (EditText) findViewById(R.id.intro_u);
        uINTRO.setText(intro);

        uRPrice = (EditText) findViewById(R.id.rPrice_u);
        uRPrice.setText(rentPrice);

        uFPrice = (EditText) findViewById(R.id.fPrice_u);
        uFPrice.setText(fullPrice);

        uCATEGORY = (Spinner) findViewById(R.id.category_u);
        uCATEGORY.setSelection(getIndex_SpinnerItem(uCATEGORY, category));

        uLANG = (Spinner) findViewById(R.id.lang_u);
        uLANG.setSelection(getIndex_SpinnerItem(uLANG, lang));

        uUpdate = (Button) findViewById(R.id.UpdateBtn_u);
        uDelete = (Button)findViewById(R.id.DeleteBtn_u);
        uCancel = (Button) findViewById(R.id.CancelBtn_u);

        //update button
        uUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setBookNo(uISBN.getText().toString());
                book.setBookTitle(uTITLE.getText().toString());
                book.setAuther(uAUTHER.getText().toString());
                book.setSize(uSIZE.getText().toString());
                book.setIntro(uINTRO.getText().toString());
                book.setRentPrice(uRPrice.getText().toString());
                book.setFullPrice(uFPrice.getText().toString());
                book.setCategory(uCATEGORY.getSelectedItem().toString());
                book.setLang(uLANG.getSelectedItem().toString());

                new FirebaseDBHelper().updateBook(key, book, new FirebaseDBHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(AdminUpdateDeleteDetails.this, "Book record has been updated successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        //delete button
        uDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDBHelper().deleteBook(key, new FirebaseDBHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(AdminUpdateDeleteDetails.this, "Record Successfully deleted.", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                });
            }
        });

        //cancel button
        uCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });

        //image choose button
        mImageView = (ImageView)findViewById(R.id.chooseImage_u);

        mButtonChooseImage = (Button) findViewById(R.id.choose_u);
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

    }

    private  int getIndex_SpinnerItem(Spinner spinner, String item){
        int index = 0;
        for (int i = 0; i<spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).equals(item)){
                index = i;
                break;
            }
        }
        return index;
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

//            Picasso.get(this).load(mImageUri).into(mImageUri);
            mImageView.setImageURI(mImageUri);
        }
    }

    //get file extension
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


}
