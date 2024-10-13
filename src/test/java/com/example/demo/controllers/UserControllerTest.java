package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getUser_shouldReturnUser() {
    // Arrange
    Long userId = 1L;
    UserDto userDto = new UserDto();
    userDto.setId(userId);
    when(userService.getUserById(userId)).thenReturn(userDto);

    // Act
    ResponseEntity<UserDto> response = userController.getUser(userId);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userDto, response.getBody());
    verify(userService, times(1)).getUserById(userId);
  }

  @Test
  void updateWalletBalance_shouldReturnUpdatedUser() {
    // Arrange
    Long userId = 1L;
    Double newBalance = 10000.0;
    UserDto updatedUserDto = new UserDto();
    updatedUserDto.setId(userId);
    updatedUserDto.setWalletBalance(newBalance);

    when(userService.updateUserWalletBalance(userId, newBalance)).thenReturn(updatedUserDto);

    // Act
    ResponseEntity<UserDto> response = userController.updateWalletBalance(userId, newBalance);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedUserDto, response.getBody());
    verify(userService, times(1)).updateUserWalletBalance(userId, newBalance);
  }

  @Test
  void updateCryptoBalance_shouldReturnUpdatedUser() {
    // Arrange
    Long userId = 1L;
    Double newEthBalance = 2.0;
    Double newBtcBalance = 1.5;
    UserDto updatedUserDto = new UserDto();
    updatedUserDto.setId(userId);
    updatedUserDto.setEthBalance(newEthBalance);
    updatedUserDto.setBtcBalance(newBtcBalance);

    when(userService.updateUserCryptoBalance(userId, newEthBalance, newBtcBalance)).thenReturn(updatedUserDto);

    // Act
    ResponseEntity<UserDto> response = userController.updateCryptoBalance(userId, newEthBalance, newBtcBalance);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedUserDto, response.getBody());
    verify(userService, times(1)).updateUserCryptoBalance(userId, newEthBalance, newBtcBalance);
  }
}
