package com.paysafe.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paysafe.demo.models.StudentUrl;

public interface StudentUrlRepository extends JpaRepository<StudentUrl, String> {
    Optional<StudentUrl> findByUrl(String url);
}