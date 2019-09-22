package com.example.ebooks_admin;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class BookList extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerBookList);
        new FirebaseDBHelper().readBooks(new FirebaseDBHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Book> books, List<String> keys) {
                findViewById(R.id.loading_books_pb).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(recyclerView, BookList.this, books, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
