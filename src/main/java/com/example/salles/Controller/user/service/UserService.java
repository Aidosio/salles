package com.example.salles.Controller.user.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.salles.Entity.User;
import com.example.salles.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public List<User> findAllOwners() {
        return userRepository.findAllOwners();
    }

    public User update(UUID id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(user.getPassword());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public void enable(UUID id) {
        User user = findById(id);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void disable(UUID id) {
        User user = findById(id);
        user.setEnabled(false);
        userRepository.save(user);
    }
}
