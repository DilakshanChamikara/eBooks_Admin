package com.example.ebooks_admin;

import com.example.ebooks_admin.Model.Book;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDeleteUpdateHelper {
    private FirebaseDatabase mdatabase;
    private DatabaseReference mreference;
    private List<Book> books = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Book> books, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDeleteUpdateHelper(){
        mdatabase = FirebaseDatabase.getInstance();
        mreference = mdatabase.getReference("books");
    }


    public void updateBook(String key, Book book, final DataStatus dataStatus){
        mreference.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }


    public void deleteBook(String key, final DataStatus dataStatus){
        mreference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
