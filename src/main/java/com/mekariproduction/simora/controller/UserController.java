package com.mekariproduction.simora.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mekariproduction.simora.entities.User;
import com.mekariproduction.simora.service.UserService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/users")
@RestController
public class UserController {
  
  private final UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping("/me")
  public ResponseEntity<User> authenticatedUser() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      User currentUser = (User) authentication.getPrincipal();

      return ResponseEntity.ok(currentUser);
  }

  @GetMapping
  public ResponseEntity<Page<User>> allUsers() {
    Page <User> users = userService.allUsers();

    return ResponseEntity.ok(users);
  }

  @PostMapping("/ko")
  public String another(@RequestBody String entity) {
      return entity;
  }

}
