package com.emergn.EmergnTeskTask.controller;


import com.emergn.EmergnTeskTask.domain.User;
import com.emergn.EmergnTeskTask.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @GetMapping("/users")
    public List<User> list(
            @RequestParam(value="login", defaultValue = "empty") String login,
            @RequestParam(value="email", defaultValue = "empty") String email
    ) {
        if( Objects.equals(login, "empty") && Objects.equals(email, "empty")) return userRepo.findAll();

        List<User> list = new ArrayList<User>();

        if(!Objects.equals(login, "empty")) {
            list.add(userRepo.findByLogin(login));
        } else {
            list.add(userRepo.findByEmail(email));
        }

        return list;
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable("id") User user){
        return user;
    }


    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping("/users/{id}")
    public User update(@PathVariable("id") User userFromDB,
                       @RequestBody User userNew) {
        BeanUtils.copyProperties(userNew, userFromDB, "id");
        return userRepo.save(userFromDB);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") User user){
        userRepo.delete(user);
    }
}
