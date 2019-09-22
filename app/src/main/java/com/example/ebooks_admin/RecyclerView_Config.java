package com.example.ebooks_admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private BooksAdapter mbooksAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Book> books, List<String> keys){
        mContext = context;
        mbooksAdapter = new BooksAdapter(books, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mbooksAdapter);
    }


    class BookItemView extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mAuther;
        private TextView mISBN;
        private TextView mCategory;
        private TextView mFullPrice;

        private TextView mIntro;
        private TextView mRprice;
        private TextView mSize;
        private TextView mLang;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.book_item, parent, false));

            mTitle = (TextView)itemView.findViewById(R.id.book__Title);
            mISBN = (TextView)itemView.findViewById(R.id.book__Num);
            mAuther = (TextView)itemView.findViewById(R.id.book__Author) ;
            mCategory = (TextView)itemView.findViewById(R.id.book__Category);
            mFullPrice = (TextView)itemView.findViewById(R.id.book__FPrice);

            mIntro = (TextView)itemView.findViewById(R.id.book__Intro);
            mRprice = (TextView)itemView.findViewById(R.id.book__RPrice);
            mSize = (TextView)itemView.findViewById(R.id.book__Size);
            mLang = (TextView)itemView.findViewById(R.id.book__Lang);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, AdminUpdateDeleteDetails.class);

                    intent.putExtra("key", key);
                    intent.putExtra("auther", mAuther.getText().toString());
                    intent.putExtra("bookNo", mISBN.getText().toString());
                    intent.putExtra("bookTitle", mTitle.getText().toString());
                    intent.putExtra("category", mCategory.getText().toString());
                    intent.putExtra("fullPrice", mFullPrice.getText().toString());
                    intent.putExtra("intro", mIntro.getText().toString());
                    intent.putExtra("lang", mLang.getText().toString());
                    intent.putExtra("rentPrice", mRprice.getText().toString());
                    intent.putExtra("size", mSize.getText().toString());

                    mContext.startActivity(intent);

                }
            });
        }

        public void bind(Book book ,String key){
            mTitle.setText(book.getBookTitle());
            mISBN.setText(book.getBookNo());
            mAuther.setText(book.getAuther());
            mCategory.setText(book.getCategory());
            mFullPrice.setText(book.getFullPrice());

            mIntro.setText(book.getIntro());
            mRprice.setText(book.getRentPrice());
            mSize.setText(book.getSize());
            mLang.setText(book.getLang());

            this.key = key;
        }
    }

    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book> mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Book> mBookList, List<String> mKeys){
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }


        @Override
        public BookItemView onCreateViewHolder( ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder( BookItemView holder, int position) {
            holder.bind(mBookList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
