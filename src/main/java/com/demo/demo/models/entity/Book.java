package com.demo.demo.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=5, nullable=false, unique=true)
    private String kode;

    @Column(length=200, nullable=false)
    private String title;

    @Column(length=100, nullable=false)
    private String author;

    @Column(length=255, nullable=true)
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
