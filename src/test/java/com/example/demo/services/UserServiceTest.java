package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userMapper;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getUserById_shouldReturnUserDto_whenUserExists() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    user.setWalletBalance(100.0);
    user.setEthBalance(2.0);
    user.setBtcBalance(1.0);

    UserDto userDto = new UserDto();
    userDto.setId(userId);
    userDto.setWalletBalance(100.0);
    userDto.setEthBalance(2.0);
    userDto.setBtcBalance(1.0);

    when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
    when(userMapper.toDto(user)).thenReturn(userDto);

    // Act
    UserDto result = userService.getUserById(userId);

    // Assert
    assertEquals(userDto, result);
    verify(userRepository, times(1)).findById(userId);
  }

  @Test
  void getUserById_shouldThrowException_whenUserDoesNotExist() {
    // Arrange
    Long userId = 1L;

    when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

    // Act & Assert
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      userService.getUserById(userId);
    });

    assertEquals("User not found", thrown.getMessage());
    verify(userRepository, times(1)).findById(userId);
  }

  @Test
  void updateUserWalletBalance_shouldUpdateAndReturnUserDto() {
    // Arrange
    Long userId = 1L;
    Double newBalance = 150.0;

    User user = new User();
    user.setId(userId);
    user.setWalletBalance(100.0);

    UserDto userDto = new UserDto();
    userDto.setId(userId);
    userDto.setWalletBalance(newBalance);

    when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
    when(userMapper.toDto(user)).thenReturn(userDto);

    // Act
    UserDto result = userService.updateUserWalletBalance(userId, newBalance);

    // Assert
    assertEquals(userDto, result);
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, times(1)).save(user);
    assertEquals(newBalance, user.getWalletBalance());
  }

  @Test
  void updateUserWalletBalance_shouldThrowException_whenUserDoesNotExist() {
    // Arrange
    Long userId = 1L;
    Double newBalance = 150.0;

    when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

    // Act & Assert
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      userService.updateUserWalletBalance(userId, newBalance);
    });

    assertEquals("User not found", thrown.getMessage());
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void updateUserCryptoBalance_shouldUpdateAndReturnUserDto() {
    // Arrange
    Long userId = 1L;
    Double ethBalance = 3.0;
    Double btcBalance = 2.0;

    User user = new User();
    user.setId(userId);
    user.setEthBalance(2.0);
    user.setBtcBalance(1.0);

    UserDto userDto = new UserDto();
    userDto.setId(userId);
    userDto.setEthBalance(ethBalance);
    userDto.setBtcBalance(btcBalance);

    when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
    when(userMapper.toDto(user)).thenReturn(userDto);

    // Act
    UserDto result = userService.updateUserCryptoBalance(userId, ethBalance, btcBalance);

    // Assert
    assertEquals(userDto, result);
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, times(1)).save(user);
    assertEquals(ethBalance, user.getEthBalance());
    assertEquals(btcBalance, user.getBtcBalance());
  }

  @Test
  void updateUserCryptoBalance_shouldThrowException_whenUserDoesNotExist() {
    // Arrange
    Long userId = 1L;
    Double ethBalance = 3.0;
    Double btcBalance = 2.0;

    when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

    // Act & Assert
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      userService.updateUserCryptoBalance(userId, ethBalance, btcBalance);
    });

    assertEquals("User not found", thrown.getMessage());
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, times(0)).save(any());
  }
}
