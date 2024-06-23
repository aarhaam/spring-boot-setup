package com.mekariproduction.simora.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mekariproduction.simora.entities.User;
import com.mekariproduction.simora.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public Page<User> allUsers(){
    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));

    return userRepository.findAll(pageRequest);
  }
}
