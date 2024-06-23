package com.mekariproduction.simora.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mekariproduction.simora.dtos.LoginUserDto;
import com.mekariproduction.simora.dtos.RegisterUserDto;
import com.mekariproduction.simora.entities.User;
import com.mekariproduction.simora.repository.UserRepository;

@Service
public class AuthenticationService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(
    UserRepository userRepository,
    AuthenticationManager authenticationManager,
    PasswordEncoder passwordEncoder
  ) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User signup(RegisterUserDto input) {
    System.out.println(input.getEmail());
    User user = new User()
      .setEmail(input.getEmail()).setFullname(input.getFullname()).setPassword(passwordEncoder.encode(input.getPassword()));

    return userRepository.save(user);
  }

  public User authenticate(LoginUserDto input) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
    );

    return userRepository.findByEmail(input.getEmail()).orElseThrow();
  }
}
