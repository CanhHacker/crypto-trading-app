package com.example.demo.mappers;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDto toDto(User user) {
    UserDto dto = new UserDto();
    dto.setId(user.getId());
    dto.setWalletBalance(user.getWalletBalance());
    dto.setEthBalance(user.getEthBalance());
    dto.setBtcBalance(user.getBtcBalance());
    return dto;
  }

  public User toEntity(UserDto dto) {
    User user = new User();
    user.setId(dto.getId());
    user.setWalletBalance(dto.getWalletBalance());
    user.setEthBalance(dto.getEthBalance());
    user.setBtcBalance(dto.getBtcBalance());
    return user;
  }
}
