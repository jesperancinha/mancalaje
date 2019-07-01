package com.jofisaes.mancala.repository;


import com.jofisaes.mancala.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}