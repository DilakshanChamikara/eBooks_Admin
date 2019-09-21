package com.example.ebooks_admin.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks_admin.Interface.ItemClickListener;
import com.example.ebooks_admin.R;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView book_no, book_title, category, fullPrice, auther;
    private ItemClickListener itemClickListener;


    public BookViewHolder(View itemView) {
        super(itemView);

//        book_title = (TextView)itemView.findViewById(R.id.book__Title);
        book_no = (TextView)itemView.findViewById(R.id.book__Num);
//        auther = (TextView)itemView.findViewById(R.id.book__Auther) ;
//        category = (TextView)itemView.findViewById(R.id.book__Category);
//        fullPrice = (TextView)itemView.findViewById(R.id.book__Price);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(), false);
    }
}
