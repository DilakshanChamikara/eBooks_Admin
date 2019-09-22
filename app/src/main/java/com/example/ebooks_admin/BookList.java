package com.example.ebooks_admin;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;


public class BookList extends AppCompatActivity {

    RecyclerView recyclerView;

    //search function
    List<String> suggestList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

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
//        materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchBar);
//        loadSuggest();
//        materialSearchBar.setCardViewElevation(10);
//        materialSearchBar.addTextChangeListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                List<String> suggest = new ArrayList<>();
//                for(String search:suggest){
//                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())) {
//                        suggest.add(search);
//                    }
//                }
//                materialSearchBar.setLastSuggestions(suggest);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

    }

    private void loadSuggest() {

    }

}
