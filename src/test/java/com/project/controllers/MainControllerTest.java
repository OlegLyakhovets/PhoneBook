package com.project.controllers;

import com.project.models.Numbers;
import com.project.models.User;
import com.project.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Test Spring Boot web project
 * @author Oleg Liakhovets
 */
class MainControllerTest {

    UserRepo userRepo;
    MainController controller;
    Model model;
    User user;

    @BeforeEach
    public void beforeEach(){
        user = new User();
        user.setLastName("Trump");
        user.setFirstName("Donald");
        user.setNumber("+1112223344");
        user.setEmail("trump@mail.com");
        user.setAddress("USA");
    }

    public MainControllerTest(){
        userRepo = mock(UserRepo.class);
        controller = new MainController(userRepo);
        model = mock(Model.class);
    }

    @Test
    void errorPage() {
        assertEquals("error", controller.errorPage());
    }

    @Test
    void indexPageWithoutSearch() {
        when(userRepo.existsUserByLastName("Trump")).thenReturn(false);
        when(userRepo.findByNumbers("+0009998877")).thenReturn(null);
        assertEquals("index", controller.indexPage(null, null,  model));
    }

    @Test
    void indexPageSearchByLastName() {
        when(userRepo.existsUserByLastName("Trump")).thenReturn(true);
        when(userRepo.findByNumbers("+0009998877")).thenReturn(null);
        assertEquals("redirect:/user/{last_name}", controller.indexPage("Trump",
                null,  model));
    }
    @Test
    void indexPageSearchByNumber() {
        when(userRepo.existsUserByLastName("Trump")).thenReturn(false);
        when(userRepo.findByNumbers("+0009998877")).thenReturn(new User());
        assertEquals("redirect:/user/{last_name}", controller.indexPage(null,
                "+0009998877",  model));
    }

    @Test
    void indexPageSearchByNumberNotFound() {
        when(userRepo.existsUserByLastName("Trump")).thenReturn(false);
        when(userRepo.findByNumbers("+0009998877")).thenReturn(null);
        assertEquals("redirect:/", controller.indexPage(null,
                "+0009998877",  model));
    }

    @Test
    void addPost() {
        assertEquals("user-add", controller.addUser());
    }

    @Test
    void addRecordSuccess() {
        assertEquals("redirect:/", controller.addRecord("Donald",
                "Trump", "+0009998877", "trump@mail.com",
                "USA", model));
    }
    @Test
    void addRecordFirstNameFailed() {
        assertEquals("redirect:/error", controller.addRecord("55Donald",
                "Trump", "+0009998877", "trump@mail.com",
                "USA", model));
    }
    @Test
    void addRecordLastNameFailed() {
        assertEquals("redirect:/error", controller.addRecord("Donald",
                "33Trump", "+0009998877", "trump@mail.com",
                "USA", model));
    }
    @Test
    void addRecordNumberFailed() {
        assertEquals("redirect:/error", controller.addRecord("Donald",
                "Trump", "ABC0009998877", "trump@mail.com",
                "USA", model));
    }

    @Test
    void userByLastName() {
        List<Numbers> list = new ArrayList<>();
        list.add(new Numbers("+1112223344"));
        when(userRepo.existsUserByLastName("Trump")).thenReturn(true);
        when(userRepo.findByLastName("Trump"))
                .thenReturn(Optional.of(user));
        assertEquals("user-page", controller.userByLastName("Trump", model));
    }

    @Test
    void userPageEdit() {
        when(userRepo.existsUserByLastName("Trump")).thenReturn(true);
        when(userRepo.findByLastName("Trump")).thenReturn(Optional.of(user));
        assertEquals("user-edit", controller.userPageEdit("Trump",  model));
    }
    @Test
    void userPageEditWhenNotFound() {
        when(userRepo.existsUserByLastName("Trump")).thenReturn(false);
        when(userRepo.findByLastName("Trump")).thenReturn(Optional.of(user));
        assertEquals("redirect:/", controller.userPageEdit("Trump",  model));
    }

    @Test
    void editPost() {
        when(userRepo.findByLastName("Trump")).thenReturn(Optional.of(user));
        assertEquals("redirect:/user/{last_name}", controller.editUser("Trump",
                "Donald", "Trump", "+1112223344",
                "trump@mail.com", "USA, Washinghton", model));
    }

    @Test
    void editPostFailedNameOrNumberOrEmail() {
        when(userRepo.findByLastName("Trump")).thenReturn(Optional.of(user));
        assertEquals("redirect:/error", controller.editUser("Trump",
                "22Donald", "Trump", "ASD+1112223344",
                "trump@mail.com", "USA, Washinghton", model));
    }

    @Test
    void deletePost() {
        when(userRepo.findByLastName("Trump")).thenReturn(Optional.of(user));
        assertEquals("redirect:/", controller.deleteUser("Trump", model));
    }

    @Test
    void deleteNumber(){
        when(userRepo.findByLastName("Trump")).thenReturn(Optional.of(user));
        assertEquals("redirect:/user/{last_name}",
                controller.deleteNumber("Trump", "+1112223344", model));
    }

}