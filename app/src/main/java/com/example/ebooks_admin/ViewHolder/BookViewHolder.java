package com.example.ebooks_admin.ViewHolder;
;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks_admin.Interface.ItemClickListener;
import com.example.ebooks_admin.R;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView BookTitle;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public BookViewHolder( View itemView) {
        super(itemView);

        BookTitle = (TextView)itemView.findViewById(R.id.book__Title);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }


}
