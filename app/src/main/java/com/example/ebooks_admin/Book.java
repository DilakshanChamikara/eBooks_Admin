package com.example.ebooks_admin;

//search bar
public class Book {
    private String bookNo, bookTitle, category, fullPrice, auther, rentPrice, size, intro, lang, image;

    public Book() {

    }

    //constructor
    public Book(String bookNo, String bookTitle, String category, String fullPrice, String auther, String rentPrice, String size, String intro, String lang, String Image) {
        this.bookNo = bookNo;
        this.bookTitle = bookTitle;
        this.category = category;
        this.fullPrice = fullPrice;
        this.auther = auther;
        this.rentPrice = rentPrice;
        this.size = size;
        this.intro = intro;
        this.lang = lang;
        this.image = Image;
    }

    //getters and setters
    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(String fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
