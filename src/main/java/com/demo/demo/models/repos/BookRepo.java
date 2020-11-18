package com.demo.demo.models.repos;

import java.util.List;

import com.demo.demo.models.entity.Book;

import org.springframework.data.repository.CrudRepository;


public interface BookRepo extends CrudRepository<Book, Integer> {
    
    public Book findByKode(String kode);

    public List<Book> findByTitleLike(String title);

    // @Query("SELECT b FROM Book b WHERE b.author = :name")
    // public List<Book> cariBukuBerdasarkanAuthor(@Param("name") String author);

    public List<Book> findByAuthorName(String name);

    public List<Book> findByPriceBetween(double min, double max);
}
