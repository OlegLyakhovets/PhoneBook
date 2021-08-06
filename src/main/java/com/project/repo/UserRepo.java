package com.project.repo;

import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLastName(String firstName);
    Optional<User> findByFirstName(String lastName);
    User findByNumbers(String number);
    User findByEmail(String email);
    boolean existsUserByLastName(String model);



}
