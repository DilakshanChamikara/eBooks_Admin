package com.example.ebooks_admin;

public class AddRecord {

    String BookNo, BookTitle, Auther, Size, Intro, RentPrice, FullPrice, Category, Language;

    //default constructor
    public AddRecord(){

    }

    //constructor
    public AddRecord(String bookNo, String bookTitle, String auther, String size, String intro, String rentPrice, String fullPrice, String category, String language) {
        BookNo = bookNo;
        BookTitle = bookTitle;
        Auther = auther;
        Size = size;
        Intro = intro;
        RentPrice = rentPrice;
        FullPrice = fullPrice;
        Category = category;
        Language = language;
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

    public String getLanguage() {
        return Language;
    }

    
}
