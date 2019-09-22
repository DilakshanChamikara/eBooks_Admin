package com.example.ebooks_admin;

public class AddRecord {

    private String BookNo, BookTitle, Auther, Size, Intro, RentPrice, FullPrice, Category, Lang, ImageURI ;

    //default constructor
    public AddRecord(){

    }

    //constructor
    public AddRecord(String bookNo, String bookTitle, String auther, String size, String intro, String rentPrice, String fullPrice, String category, String lang) {
        BookNo = bookNo;
        BookTitle = bookTitle;
        Auther = auther;
        Size = size;
        Intro = intro;
        RentPrice = rentPrice;
        FullPrice = fullPrice;
        Category = category;
        Lang = lang;
//        ImageURI = imageUri;
    }

    //getters
    public String getBookNo() {
        return BookNo;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public String getAuther() {
        return Auther;
    }

    public String getSize() {
        return Size;
    }

    public String getIntro() {
        return Intro;
    }

    public String getRentPrice() {
        return RentPrice;
    }

    public String getFullPrice() {
        return FullPrice;
    }

    public String getCategory() {
        return Category;
    }

    public String getLang() {
        return Lang;
    }

    //    public String getImageURI() {
//        return ImageURI;
//    }
}
