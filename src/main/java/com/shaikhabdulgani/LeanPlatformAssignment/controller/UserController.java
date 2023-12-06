package com.shaikhabdulgani.LeanPlatformAssignment.controller;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.SaveUserDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.model.User;
import com.shaikhabdulgani.LeanPlatformAssignment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<User>> getAll(
        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize
    ){
        return ResponseEntity.ok(userService.getAll(pageNumber, pageSize));
    }

    @PostMapping("user")
    public ResponseEntity<User> save(@RequestBody @Valid SaveUserDAO req){
        return new ResponseEntity<>(userService.save(req), HttpStatus.CREATED);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) throws NotFound {
        return ResponseEntity.ok(userService.get(userId));
    }

}
