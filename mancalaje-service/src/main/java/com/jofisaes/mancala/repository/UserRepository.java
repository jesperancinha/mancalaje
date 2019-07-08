package com.jofisaes.mancala.repository;


import com.jofisaes.mancala.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User, String>, Serializable {

}