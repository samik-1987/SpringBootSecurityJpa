package com.example.security;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsModel, Integer>{    
    
    Optional<UserDetailsModel> findByUsername(String username);
}