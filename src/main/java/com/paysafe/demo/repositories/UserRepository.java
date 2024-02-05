package com.paysafe.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paysafe.demo.dtos.UserDto;
import com.paysafe.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
