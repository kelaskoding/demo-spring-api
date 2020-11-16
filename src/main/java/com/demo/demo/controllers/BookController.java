package com.demo.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.demo.demo.dto.BookData;
import com.demo.demo.models.entity.Book;
import com.demo.demo.models.repos.FakeBookRepo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    
    private FakeBookRepo repo = new FakeBookRepo();

    @GetMapping
    public List<BookData> findAll(){
        List<BookData> listOfBookData = new ArrayList<>();
        List<Book> listOfBook = repo.findAll();
        for(Book book : listOfBook){
            listOfBookData.add(new BookData(book.getId(),book.getKode(),book.getTitle(),book.getAuthor(),book.getDescription(),book.getPrice()));
        }
        return listOfBookData;
    }

    @GetMapping("/{kode}")
    public Book findByKode(@PathVariable("kode") String kode){
        return repo.findByKode(kode);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return repo.save(book);
    }

    @DeleteMapping("/{kode}")
    public boolean removeByKode(@PathVariable("kode") String kode){
        repo.remove(kode);
        return true;
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
        return repo.update(book);
    }

}
