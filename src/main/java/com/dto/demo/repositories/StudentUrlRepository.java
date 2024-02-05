package com.dto.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dto.demo.models.StudentUrl;

public interface StudentUrlRepository extends JpaRepository<StudentUrl, String> {
    Optional<StudentUrl> findByUrl(String url);
}