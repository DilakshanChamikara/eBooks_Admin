package com.example.ebooks_admin;

public class ImageUpload {

//    private String mName;
    private String mImageUrl;

    public ImageUpload() {
    }

    public ImageUpload( String mImageUrl) {


//        this.mName = mName;
        this.mImageUrl = mImageUrl;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
