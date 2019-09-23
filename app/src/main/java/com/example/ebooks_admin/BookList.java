package com.example.ebooks_admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks_admin.Interface.ItemClickListener;
import com.example.ebooks_admin.ViewHolder.BookViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class BookList extends AppCompatActivity {

    RecyclerView recyclerView;

    FirebaseDatabase database;
    DatabaseReference bookList;
    private DatabaseReference databaseReference;
    String categoryId = "";

    //search function
    FirebaseRecyclerAdapter<Book, BookViewHolder> searchAdapter;
    List<String> suggestList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        bookList = database.getReference("books");

        //recycler View
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

        //search
        materialSearchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        materialSearchBar.setHint("Search by title");
        loadSuggest();
        materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<>();
                for(String search:suggest){
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

    }

    private void startSearch(CharSequence text){
        Query firebaseSearchQuery = databaseReference.orderByChild("isbn").equalTo(categoryId);

        FirebaseRecyclerOptions<Book> options =
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(firebaseSearchQuery, Book.class)
                        .setLifecycleOwner(this)
                        .build();

        FirebaseRecyclerAdapter<Book, BookViewHolder> adapter = new FirebaseRecyclerAdapter<Book, BookViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i, Book model) {
                bookViewHolder.BookTitle.setText(model.getBookTitle());

                final Book local = model;
                bookViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(BookList.this, AdminUpdateDeleteDetails.class);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };

        //set adapter
        Log.d("TAG", "" + adapter.getItemCount());
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    private void loadSuggest() {
        bookList.orderByChild("isbn").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                            Book item  = postSnapshot.getValue(Book.class);
                            suggestList.add(item.getBookTitle());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
