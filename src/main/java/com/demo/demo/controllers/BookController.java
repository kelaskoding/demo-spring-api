package com.demo.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.demo.demo.dto.BookData;
import com.demo.demo.dto.ResponseData;
import com.demo.demo.dto.SearchData;
import com.demo.demo.helpers.DateTimeFormat;
import com.demo.demo.models.entity.Book;
import com.demo.demo.models.repos.BookRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
    
    @Autowired
    private BookRepo repo;

    @GetMapping
    public ResponseEntity<?> findAll(){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            List<BookData> listOfBookData = new ArrayList<>();
            Iterable<Book> listOfBook = repo.findAll();
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

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            Book book = repo.findById(id).get();
            if(book==null){
                response.setStatus(false);
                response.getMessages().add("Id not found");
                response.setResponseDateTime(DateTimeFormat.format(new Date()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.setStatus(true);
            response.getMessages().add("Id found");
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
    public ResponseEntity<?> saveBook(@Valid @RequestBody BookData bookData, Errors errors) {
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        
        if(errors.hasErrors()) {
            for(ObjectError error: errors.getAllErrors()){
                response.getMessages().add(error.getDefaultMessage());
            }
            response.setStatus(false);            
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try{
            Book book = new Book();
            book.setKode(bookData.getKode());
            book.setTitle(bookData.getTitle());
            book.setDescription(bookData.getDescription());
            book.setAuthor(bookData.getAuthor());
            book.setPrice(bookData.getPrice());

            response.setStatus(true);
            response.getMessages().add("Book saved");
            response.setPayload(repo.save(book));
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeById(@PathVariable("id") int id){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            repo.deleteById(id);
            response.setStatus(true);
            response.getMessages().add("Book removed");
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookData bookData, @PathVariable("id") int id){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            Book book = new Book();
            book.setId(id);
            book.setKode(bookData.getKode());
            book.setTitle(bookData.getTitle());
            book.setDescription(bookData.getDescription());
            book.setAuthor(bookData.getAuthor());
            book.setPrice(bookData.getPrice());

            response.setStatus(true);
            response.getMessages().add("Book updated");
            response.setPayload(repo.save(book));
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/kode/{kode}")
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

    @PostMapping("/search")
    public ResponseEntity<?> findByTitle(@RequestBody SearchData data){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            List<Book> books = repo.findByTitleLike("%"+data.getSearchKey()+"%");
            if(books.size()>0){
                response.setStatus(true);
                response.getMessages().add("Books found");
                response.setPayload(books);
                response.setResponseDateTime(DateTimeFormat.format(new Date()));
                return ResponseEntity.ok(response);
            }else{
                response.setStatus(false);
                response.getMessages().add("Books not found");
                response.setResponseDateTime(DateTimeFormat.format(new Date()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/search/author")
    public ResponseEntity<?> findByAuthor(@RequestBody SearchData data){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            List<Book> books = repo.findByAuthor(data.getSearchKey());
            if(books.size()>0){
                response.setStatus(true);
                response.getMessages().add("Books found");
                response.setPayload(books);
                response.setResponseDateTime(DateTimeFormat.format(new Date()));
                return ResponseEntity.ok(response);
            }else{
                response.setStatus(false);
                response.getMessages().add("Books not found");
                response.setResponseDateTime(DateTimeFormat.format(new Date()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/price/{min}/to/{max}")
    public ResponseEntity<?> findByPriceRange(@PathVariable("min") double min, @PathVariable("max") double max){
        ResponseData response = new ResponseData();
        response.setRequestDateTime(DateTimeFormat.format(new Date()));
        try{
            List<Book> books = repo.findByPriceBetween(min, max);
            if(books.size()>0){
                response.setStatus(true);
                response.getMessages().add("Books found");
                response.setPayload(books);
                response.setResponseDateTime(DateTimeFormat.format(new Date()));
                return ResponseEntity.ok(response);
            }else{
                response.setStatus(false);
                response.getMessages().add("Books not found");
                response.setResponseDateTime(DateTimeFormat.format(new Date()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch(Exception e){
            response.setStatus(false);
            response.getMessages().add(e.getMessage());
            response.setResponseDateTime(DateTimeFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
