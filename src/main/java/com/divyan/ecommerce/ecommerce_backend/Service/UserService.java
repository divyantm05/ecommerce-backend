package com.divyan.ecommerce.ecommerce_backend.Service;

import com.divyan.ecommerce.ecommerce_backend.Entity.User;
import com.divyan.ecommerce.ecommerce_backend.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        if(user==null){
            return null;
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        Optional<User>user=userRepository.findById(id);
        return user.orElse(null);
    }
    public User updateUser(Long id, User user) {
        Optional<User> user1=userRepository.findById(id);
        User u=user1.orElse(null);
        return u;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
