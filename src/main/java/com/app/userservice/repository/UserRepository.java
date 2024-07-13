package com.app.userservice.repository;

import com.app.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user); // update + insert
    Optional<User> findByEmail(String email);

}
