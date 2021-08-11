package com.project.repo;

import com.project.models.Numbers;
import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * Test Spring Boot web project
 * @author Oleg Liakhovets
 */

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLastName(String lastName);
    Optional<User> findByFirstName(String lastName);
    @Query(value = "select id, address, email, first_name, last_name " +
            "from phonebook.user, phonebook.user_numbers where  number = :number\n" +
            "and user_id=id;", nativeQuery = true)
    User findByNumbers(@Param("number")String number);
    User findByEmail(String email);
    boolean existsUserByLastName(String lastName);
    @Modifying
    @Transactional()
    @Query(value = "DELETE FROM phonebook.user_numbers WHERE user_numbers.number = ?1", nativeQuery = true)
    void deleteNumberByNumber(String number);



}
