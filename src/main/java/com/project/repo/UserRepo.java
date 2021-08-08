package com.project.repo;

import com.project.models.Numbers;
import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLastName(String firstName);
    Optional<User> findByFirstName(String lastName);
    @Query(value = "select id, address, email, first_name, last_name " +
            "from phonebook.user, phonebook.user_numbers where  number = :number\n" +
            "and user_id=id;", nativeQuery = true)
    User findByNumbers(@Param("number")String number);
    User findByEmail(String email);
    boolean existsUserByLastName(String model);
    @Modifying
    @Query(value = "DELETE number FROM phonebook.number WHERE number = :number",nativeQuery = true)
    int deleteNumberByNumber(@Param("number") String number);



}
