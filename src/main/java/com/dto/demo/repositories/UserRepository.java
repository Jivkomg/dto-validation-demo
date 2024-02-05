package com.dto.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dto.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
