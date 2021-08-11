package com.project.controllers;

import com.project.models.Numbers;
import com.project.models.User;
import com.project.repo.UserRepo;
import com.project.service.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Test Spring Boot web project
 * @author Oleg Liakhovets
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final UserRepo repo;
    private User user;

    @Autowired
    public MainController(UserRepo repo){
        this.repo = repo;
    }

    /** Method returns error page
    */
    @GetMapping("/error")
    public String errorPage(){
        return "error";
    }

    /** Home page method. Implements handling search by Last name or by phone number.
    */
     @GetMapping("/")
    public String indexPage(@RequestParam(name = "last_name", required = false) String last_name,
                            @RequestParam(name = "number", required = false) String number,
                            Model model){
        if(last_name != null)
        {
            if(!repo.existsUserByLastName(last_name)) return "redirect:/";  //check for exist
            Optional<User> users = repo.findByLastName(last_name);
            model.addAttribute("users", users);
            model.addAttribute("last_name", last_name);
            return "redirect:/user/{last_name}";
        }
        if(number != null)
        {
            User userF = repo.findByNumbers(number);
            if(userF == null) return "redirect:/";   //check for exist
            model.addAttribute("last_name", userF.getLastName());
            model.addAttribute("users", userF);
            model.addAttribute("number", number);
            return "redirect:/user/{last_name}";
        }
        Iterable<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    /** page for adding new record to phonebook
    */
     @GetMapping("/add")
    public String addUser(){
        return "user-add";
    }

    /** saving new record to phonebook. Checks data for compliance
    */
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

    /** Returns page of certain user by Last Name
    */
    @GetMapping("/user/{last_name}")
    public String userByLastName(@PathVariable(name = "last_name") String lastN, Model model){
        if(!repo.existsUserByLastName(lastN)) return "redirect:/";  //check for exist
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

    /** Edit page of certain user. Find user in database by last name, show
    * data on client's page
    */
     @GetMapping("/user/{last_name}/edit")
    public String userPageEdit(@PathVariable(name = "last_name") String last_name,
                               Model model){
        if(!repo.existsUserByLastName(last_name)) return "redirect:/";
        Optional<User> posts = repo.findByLastName(last_name);
        String f_name = repo.findByLastName(last_name).get().getFirstName();
        String l_name = repo.findByLastName(last_name).get().getLastName();
        List<Numbers> numbers = repo.findByLastName(last_name)
                .get().getNumbers()
                .stream().toList();
        String email = repo.findByLastName(last_name).get().getEmail();
        String address = repo.findByLastName(last_name).get().getAddress();
        model.addAttribute("first_name", f_name);
        model.addAttribute("last_name", l_name);
        model.addAttribute("numbers", numbers);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        return "user-edit";
    }

    /** Saves edited data to database
    */
     @PostMapping("/user/{last_name}/edit")
    public String editUser(@PathVariable(name = "last_name") String lName,
                           @RequestParam String first_name, @RequestParam String last_name,
                           @RequestParam String number,
                           @RequestParam String email,
                           @RequestParam String address, Model model){
        user = repo.findByLastName(last_name).orElseThrow();
        if(UserValidate.nameValidate(first_name)) user.setFirstName(first_name);
        else return "redirect:/error";
        if(UserValidate.nameValidate(last_name)) user.setLastName(last_name);
        else return "redirect:/error";
        if(UserValidate.numberValidate(number) &&
        !user.getNumbers().contains(number))  user.setNumber(number);
        else return "redirect:/error";
        if(UserValidate.emailValidate(email))user.setEmail(email);
        else return "redirect:/error";
        user.setAddress(address);
        repo.save(user);
        return "redirect:/user/{last_name}";
    }

    /** Delete user from database
    */
     @PostMapping("/user/{last_name}/delete")
    public String deleteUser(@PathVariable(name = "last_name") String last_name,
                              Model model) {
        User userDelete = repo.findByLastName(last_name).orElseThrow();
        repo.delete(userDelete);
        return "redirect:/";
    }

    /** Deleting number
    */
    @PostMapping("/user/{last_name}/deletenumber")
    public String deleteNumber(@PathVariable(name = "last_name") String last_name,
                               @RequestParam String number,
                                Model model) {
        User userDelete = repo.findByLastName(last_name).orElseThrow();
        repo.deleteNumberByNumber(number);
        return "redirect:/user/{last_name}";
    }
}
