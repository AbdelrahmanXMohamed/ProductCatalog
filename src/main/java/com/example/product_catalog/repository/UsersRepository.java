package com.example.product_catalog.repository;

import com.example.product_catalog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);


}

