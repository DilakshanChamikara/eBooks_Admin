package com.example.ebooks_admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AdminUpdateDeleteDetails extends AppCompatActivity {

//    DatabaseReference ref;
//    ArrayList<Book> list;
//
//    RecyclerView recyclerView;
//    SearchView searchView;

    private Button uUpdate, uDelete, uCancel;
    private EditText uISBN, uTITLE, uAUTHER, uSIZE, uINTRO, uRPrice, uFPrice, uIMAGE;
    private Spinner uCATEGORY, uLANG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_delete_details);

//        ref = FirebaseDatabase.getInstance().getReference().child("ebookadmin-a799f").child("books");
//        recyclerView = findViewById(R.id.rv);
       // searchView = findViewById(R.id.searchView);


        //find Ids
        uISBN = (EditText) findViewById(R.id.isbn_u);
        uTITLE = (EditText) findViewById(R.id.title_u);
        uAUTHER = (EditText) findViewById(R.id.auther_u);
        uSIZE = (EditText) findViewById(R.id.size_u);
        uINTRO = (EditText) findViewById(R.id.intro_u);
        uRPrice = (EditText) findViewById(R.id.rPrice_u);
        uFPrice = (EditText) findViewById(R.id.fPrice_u);

        uCATEGORY = (Spinner) findViewById(R.id.category_u);
        uLANG = (Spinner) findViewById(R.id.lang_u);

        uUpdate = (Button) findViewById(R.id.UpdateBtn_u);
        uDelete = (Button)findViewById(R.id.DeleteBtn_u);
        uCancel = (Button) findViewById(R.id.CancelBtn_u);

    }


//    //search bar
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if(ref != null){
//            ref.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if(dataSnapshot.exists()){
//
//                        list = new ArrayList<>();
//
//                        for(DataSnapshot ds : dataSnapshot.getChildren()){
//                            list.add(ds.getValue(Book.class));
//                        }
//                        AdapterClass adapterClass = new AdapterClass(list);
//                        recyclerView.setAdapter(adapterClass);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(AdminUpdateDeleteDetails.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        if(searchView != null){
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String s) {
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String s) {
//                    search(s);
//                    return true;
//                }
//            });
//        }
//    }
//
//    //search function
//    private void search(String str){
//
//        ArrayList<Book> myList = new ArrayList<>();
//        for(Book object : list){
//            if(object.getBookTitle().toLowerCase().contains(str.toLowerCase())){
//                myList.add(object);
//            }
//        }
//
//        AdapterClass adapterClass = new AdapterClass(myList);
//        recyclerView.setAdapter(adapterClass);
//    }

}
