package com.divyan.ecommerce.ecommerce_backend.Controller;

import com.divyan.ecommerce.ecommerce_backend.Entity.User;
import com.divyan.ecommerce.ecommerce_backend.Respository.UserRepository;
import com.divyan.ecommerce.ecommerce_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<User> getUserById(@RequestParam("id") Long id) {
        Optional<User>user= Optional.ofNullable(userService.getUserById(id));
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
