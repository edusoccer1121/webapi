package com.core.webapi.repository;

import com.core.webapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getById(Long id);

    User getByUsername(String username);

    List<User> getByUsernameLike(String username);

    List<User> findAll();


    @Transactional
    @Modifying
    @Query("UPDATE User U " +
            "SET U.enabled = '0' " +
            "WHERE U.id = :userId")
    void deactivateUser(@Param("userId") Long userId);


    @Transactional
    @Modifying
    @Query("UPDATE User U " +
            "SET U.enabled = '0' " +
            "WHERE U IN :users")
    void deactivateUsers(@Param("users") List<User> users);


}
