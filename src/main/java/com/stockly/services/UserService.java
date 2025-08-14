package com.stockly.services;

import com.stockly.entities.User;
import com.stockly.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        repository.save(user);
        return user;
    }

    public User updateUser(Long id, User obj){
        User user = getUserById(id);

        user.setName(
                obj.getName() != null && !obj.getName().isBlank()
                ? obj.getName() : user.getName()
        );
        user.setEmail(
                obj.getEmail() != null && !obj.getEmail().isBlank()
                ? obj.getEmail() : user.getEmail()
        );

        user.setUpdatedAt(LocalDateTime.now());
        repository.save(user);
        return user;
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
}
