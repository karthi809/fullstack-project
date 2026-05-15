package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<userEntity,Integer> {
    Optional<userEntity> findByEmail(String email);
}
