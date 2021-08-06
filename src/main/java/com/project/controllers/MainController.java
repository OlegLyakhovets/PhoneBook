package com.project.controllers;

import com.project.models.Numbers;
import com.project.models.User;
import com.project.repo.UserRepo;
import com.project.service.UserValidate;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserRepo repo;
    private User user;

    @Autowired
    public MainController(UserRepo repo){
        this.repo = repo;
    }

    @GetMapping("/error")
    public String errorPage(Model model){
        return "error";
    }

    @GetMapping("/")
    public String indexPage(Model model){
        Iterable<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/home")
    public String indexHome(Model model){
        Iterable<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/add")
    public String addPost(){
        return "user-add";
    }
    @PostMapping("/add")
    public String addRecord(@RequestParam String firstName, @RequestParam String lastName,
                            @RequestParam String number,
                            @RequestParam String email,
                            @RequestParam String address, Model model){
        user = new User();
        if(UserValidate.nameValidate(firstName)) user.setFirstName(firstName);
        else return "redirect:/error";
        if(UserValidate.nameValidate(lastName)) user.setLastName(lastName);
        else return "redirect:/error";
        if(UserValidate.numberValidate(number))user.setNumber(number);
        else return "redirect:/error";
        user.setEmail(email);
        user.setAddress(address);
        repo.save(user);
        return "redirect:/";
    }

    @GetMapping("/user/{last_name}")
    public String userByLastName(@PathVariable(name = "last_name") String lastN, Model model){
        if(!repo.existsUserByLastName(lastN)) return "redirect:/";
        Optional<User> users = repo.findByLastName(lastN);
        String firstName = repo.findByLastName(lastN).get().getFirstName();
        String lastName = repo.findByLastName(lastN).get().getLastName();
        List<Numbers> numbers = repo.findByLastName(lastN).get().getNumbers().stream().toList();
        String email = repo.findByLastName(lastN).get().getEmail();
        String address = repo.findByLastName(lastN).get().getAddress();

        model.addAttribute("first_name", firstName);
        model.addAttribute("last_name", lastName);
        model.addAttribute("number", numbers);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        return "user-page";
    }


}
