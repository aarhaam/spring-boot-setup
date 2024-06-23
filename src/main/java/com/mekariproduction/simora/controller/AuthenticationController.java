package com.mekariproduction.simora.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mekariproduction.simora.dtos.LoginResponseDto;
import com.mekariproduction.simora.dtos.LoginUserDto;
import com.mekariproduction.simora.dtos.RegisterUserDto;
import com.mekariproduction.simora.entities.User;
import com.mekariproduction.simora.service.AuthenticationService;
import com.mekariproduction.simora.service.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {
  
  private final JwtService jwtService;
  private final AuthenticationService authenticationService;

  public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService){
    this.jwtService = jwtService;
    this.authenticationService = authenticationService;
  }

  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    User registeredUser = authenticationService.signup(registerUserDto);

    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
    User authenticateUser = authenticationService.authenticate(loginUserDto);
    String jwtToken = jwtService.generateToken(authenticateUser);

    LoginResponseDto loginResponseDto = new LoginResponseDto().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

    return ResponseEntity.ok(loginResponseDto);
  }  

  @GetMapping("/")
  public ResponseEntity<String> allUsers(@RequestParam String param) {
      System.out.println("running");
      // List <User> users = userService.allUsers();
      String responseMessage = "Received data: ";
      return ResponseEntity.ok().body(responseMessage);
  }

  @PostMapping("/users/ko")
  public String another(@RequestBody String entity) {
      return entity;
  }

}
