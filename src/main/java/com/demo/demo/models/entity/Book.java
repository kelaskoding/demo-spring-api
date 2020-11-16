package com.demo.demo.models.entity;

public class Book {
    private int id;
    private String kode;
    private String title;
    private String author;
    private String description;
    private double price;

    public Book() {
    }

    public Book(int id, String kode, String title, String author, String description, double price) {
        this.id = id;
        this.kode = kode;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
