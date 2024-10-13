package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @PutMapping("/{id}/wallet-balance")
  public ResponseEntity<UserDto> updateWalletBalance(@PathVariable Long id, @RequestParam Double balance) {
    return ResponseEntity.ok(userService.updateUserWalletBalance(id, balance));
  }

  @PutMapping("/{id}/crypto-balance")
  public ResponseEntity<UserDto> updateCryptoBalance(@PathVariable Long id,
      @RequestParam Double ethBalance,
      @RequestParam Double btcBalance) {
    return ResponseEntity.ok(userService.updateUserCryptoBalance(id, ethBalance, btcBalance));
  }
}