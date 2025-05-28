package com.example.user_service.repository;

import com.example.user_service.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailEndingWith(String domain);

   Optional<User> findByEmail(String email); 
}
