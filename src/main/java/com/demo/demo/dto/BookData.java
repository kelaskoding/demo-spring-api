package com.demo.demo.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookData {
    private int id;

    @NotEmpty(message="Kode is required")
    @Size(min=3, max=5, message="Kode length must be 3 to 5 characters")
    @Pattern(regexp="BK[0-9]+", message= "Kode must be start with BK")
    private String kode;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Author is required")
    private String author;

    private String description;
    
    @DecimalMin(value="1.0")
    private double price;

    public BookData() {
    }

    public BookData(int id, String kode, String title, String author,String description, double price) {
        this.id = id;
        this.kode = kode;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
