package com.example.salles.Controller.user;

import com.example.salles.Controller.user.service.UserService;
import com.example.salles.Entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/user", produces = "application/json;charset=UTF-8")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/owner")
    public List<User> findAllOwners() {
        return userService.findAllOwners();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

    @PostMapping("/{id}/enable")
    public ResponseEntity<Void> enable(@PathVariable UUID id) {
        userService.enable(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/disable")
    public ResponseEntity<Void> disable(@PathVariable UUID id) {
        userService.disable(id);
        return ResponseEntity.ok().build();
    }

}
