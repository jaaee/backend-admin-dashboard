package com.example.admin_dashboard.repository;

import com.example.admin_dashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameAndPassword(String userName, String password);

    @Query("""
       SELECT COUNT(s)
       FROM User s
       WHERE s.sessionActive = true
       """)
    Long countActiveUsers();
}
