package com.example.ebooks_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks_admin.Interface.ItemClickListener;
import com.example.ebooks_admin.Model.Book;
import com.example.ebooks_admin.ViewHolder.BookViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class BookList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference bookList;

    private Query query;
    FirebaseRecyclerAdapter<Book,BookViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        bookList = database.getReference("books");

        recyclerView  = (RecyclerView)findViewById(R.id.recyclerBookList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadList();

    }

    private void loadList() {

        FirebaseRecyclerOptions<Book> options =
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(query, Book.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Book, BookViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i, @NonNull Book model) {
//                bookViewHolder.book_title.setText(model.getBookTitle());
                bookViewHolder.book_no.setText(model.getBookNo());
//                bookViewHolder.auther.setText(model.getAuther());
//                bookViewHolder.category.setText(model.getCategory());
//                bookViewHolder.fullPrice.setText(model.getFullPrice());
                final Book clickItem = model;

                bookViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent updateDelete = new Intent(BookList.this, AdminUpdateDeleteDetails.class);
                        updateDelete.putExtra("bookNo", adapter.getRef(position).getKey());
                    }
                });
            }

            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.book_item, parent, false);
                return new BookViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
