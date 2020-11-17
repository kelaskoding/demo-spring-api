package com.demo.demo.models.repos;

import com.demo.demo.models.entity.Book;

import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Integer> {
    
}
