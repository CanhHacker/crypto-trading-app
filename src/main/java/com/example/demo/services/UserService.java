package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  public UserDto getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return userMapper.toDto(user);
  }

  public UserDto updateUserWalletBalance(Long userId, Double newBalance) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    user.setWalletBalance(newBalance);
    userRepository.save(user);
    return userMapper.toDto(user);
  }

  public UserDto updateUserCryptoBalance(Long userId, Double ethBalance, Double btcBalance) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    user.setEthBalance(ethBalance);
    user.setBtcBalance(btcBalance);
    userRepository.save(user);
    return userMapper.toDto(user);
  }
}