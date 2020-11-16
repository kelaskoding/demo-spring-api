package com.demo.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.demo.dto.BookData;
import com.demo.demo.dto.ResponseData;
import com.demo.demo.helpers.DateTimeFormat;
import com.demo.demo.models.entity.Book;
import com.demo.demo.models.repos.FakeBookRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> findAll(){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            List<BookData> listOfBookData = new ArrayList<>();
            List<Book> listOfBook = repo.findAll();
            for(Book book : listOfBook){
                listOfBookData.add(new BookData(book.getId(),book.getKode(),book.getTitle(),book.getAuthor(),book.getDescription(),book.getPrice()));
            }
            response.setStatus(true);
            response.getMessages().add("Load data books");
            response.setPayload(listOfBookData);
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.setStatus(false);
            response.getMessages().add(e.getMessage());
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }       
    }

    @GetMapping("/{kode}")
    public ResponseEntity<?> findByKode(@PathVariable("kode") String kode){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            Book book = repo.findByKode(kode);
            if(book==null){
                response.setStatus(false);
                response.getMessages().add("Kode not found");
                response.setResponseDateTime(DateTimeFormat.format(new Date()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.setStatus(true);
            response.getMessages().add("Kode found");
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            response.setPayload(book);
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.setStatus(false);
            response.getMessages().add(e.getMessage());
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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
