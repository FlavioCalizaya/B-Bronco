package com.infinity.bronco.controllers;

import com.infinity.bronco.models.User;
import com.infinity.bronco.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Component
@RestController
@RequestMapping("api/v1/users")
@CrossOrigin()
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User addedUser = userService.saveUser( user );
        return  ResponseEntity.ok( addedUser);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable("id") Integer idUser) {
        Optional<User> user = userService.getUserById(idUser);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User updatedUser) {
            User updated = userService.updateUser(id,updatedUser);
            return ResponseEntity.ok(updated);
    }

    @PutMapping( path = "/remove/{id}")
    public ResponseEntity<User> removeUser( @PathVariable Integer id) {
        User updated = userService.removeUser(id);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUser() {
        return ResponseEntity.ok(userService.getUser());
    }
}
