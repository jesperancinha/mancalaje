package com.jofisaes.mancala.repository;


import com.jofisaes.mancala.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Repository
@Transactional(readOnly = false, propagation = REQUIRES_NEW)
public interface UserRepository extends JpaRepository<User, String> {

    @Transactional(propagation = REQUIRES_NEW)
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM User u WHERE u.email = :email")
    void deleteByEmail(@Param("email") String email);
}