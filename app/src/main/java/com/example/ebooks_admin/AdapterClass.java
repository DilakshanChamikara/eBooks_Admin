package com.example.ebooks_admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//search bar
public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{

    ArrayList<Book> list;
    public AdapterClass(ArrayList<Book> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.ISBN.setText(list.get(i).getBookNo());
        myViewHolder.TITLE.setText(list.get(i).getBookTitle());
        myViewHolder.AUTHER.setText(list.get(i).getAuther());
        myViewHolder.SIZE.setText(list.get(i).getSize());
        myViewHolder.INTRO.setText(list.get(i).getIntro());
        myViewHolder.RPrice.setText(list.get(i).getRentPrice());
        myViewHolder.FPrice.setText(list.get(i).getFullPrice());
        myViewHolder.FPrice.setText(list.get(i).getFullPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ISBN, TITLE, AUTHER, SIZE, INTRO, RPrice, FPrice;
        Spinner CATEGORY, LANG;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ISBN = itemView.findViewById(R.id.isbn);
            TITLE = itemView.findViewById(R.id.title);

        }
    }

}
