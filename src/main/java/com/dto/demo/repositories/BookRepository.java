package com.dto.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dto.demo.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByTitleIn(List<String> bookTitles);
}
