package com.project.repo;

import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLastName(String firstName);
    Optional<User> findByFirstName(String lastName);
    User findByNumbers(String number);
    User findByEmail(String email);
    boolean existsUserByLastName(String model);
    @Modifying
    @Query(value = "DELETE number FROM phonebook.number WHERE number = :number",nativeQuery = true)
    int deleteNumberByNumber(@Param("number") String number);



}
