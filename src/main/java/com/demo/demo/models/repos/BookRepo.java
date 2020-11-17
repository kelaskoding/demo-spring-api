package com.demo.demo.models.repos;

import java.util.List;

import com.demo.demo.models.entity.Book;

import org.springframework.data.repository.CrudRepository;


public interface BookRepo extends CrudRepository<Book, Integer> {
    
    public Book findByKode(String kode);

    public List<Book> findByTitle(String title);
}
