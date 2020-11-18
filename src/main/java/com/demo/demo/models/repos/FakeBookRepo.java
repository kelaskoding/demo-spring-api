package com.demo.demo.models.repos;

import java.util.ArrayList;
import java.util.List;

import com.demo.demo.models.entity.Book;

import org.springframework.stereotype.Component;

@Component
public class FakeBookRepo {
    
    private List<Book> listOfBooks  = new ArrayList<>(); 

    public FakeBookRepo(){
        // listOfBooks.add(new Book(1, "BK001","Sample Book 001","Hendro","No Description",20000));
        // listOfBooks.add(new Book(2, "BK002","Sample Book 002","Budi","No Description",35000));
        // listOfBooks.add(new Book(3, "BK003","Sample Book 003","Herman","No Description",40000));
        // listOfBooks.add(new Book(4, "BK004","Sample Book 004","Agus","No Description",50000));
        // listOfBooks.add(new Book(5, "BK005","Sample Book 005","Yuli","No Description",35000));
    }

    public List<Book> findAll(){
        return this.listOfBooks;
    }

    public Book findByKode(String kode){
        Book output = null;
        for(Book book: this.listOfBooks){
            if(book.getKode().equals(kode)){
                output = book;
                break;
            }
        }
        return output;
    }

    public Book save(Book book){
        book.setId(this.listOfBooks.size()+1);
        this.listOfBooks.add(book);
        return book;
    }

    public Book update(Book book){
        Book output = null;
        for(Book temp: this.listOfBooks){
            if(temp.getId()==book.getId()){
                temp.setKode(book.getKode());
                temp.setTitle(book.getTitle());
                temp.setAuthor(book.getAuthor());
                temp.setDescription(book.getDescription());
                temp.setPrice(book.getPrice());
                output = temp;
                break;
            }
        }
        return output;
    }

    public void remove(String kode){
        for(Book book: this.listOfBooks){
            if(book.getKode().equals(kode)){
               this.listOfBooks.remove(book);
               break;
            }
        }
    }
}
