package com.paysafe.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paysafe.demo.models.Book;
import com.paysafe.demo.models.User;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByTitleIn(List<String> bookTitles);
}
