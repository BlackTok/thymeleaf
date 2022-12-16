package ru.gb.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Users;
import ru.gb.service.DatabaseUserDetailService;

import java.util.List;

@RestController
public class AdminController {
    private final DatabaseUserDetailService userDetailService;

    public AdminController(DatabaseUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/admin")
    public List<Users> getAllUsers() {
        return userDetailService.getAllUsers();
    }

    @PostMapping("/admin")
    public ResponseEntity addUser(@RequestBody Users user) {
        userDetailService.addUser(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userDetailService.deleteUser(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/admin/{id}/{add}")
    public ResponseEntity changeRole(@RequestBody Users user, @PathVariable Long roleId, @PathVariable Boolean add) {
        userDetailService.changeRole(user, roleId, add);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
