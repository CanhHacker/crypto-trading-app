package com.example.demo.services;

import com.example.demo.dtos.TradeDto;
import com.example.demo.entities.Trade;
import com.example.demo.entities.User;
import com.example.demo.mappers.TradeMapper;
import com.example.demo.repositories.TradeRepository;
import com.example.demo.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

  @Autowired
  private TradeRepository tradeRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TradeMapper tradeMapper;

  public TradeDto placeTrade(TradeDto tradeDto) {
    User user = userRepository.findById(tradeDto.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));

    Trade trade = tradeMapper.toEntity(tradeDto, user);
    trade = tradeRepository.save(trade);

    if (tradeDto.getTradeType().equalsIgnoreCase("BUY")) {
      user.setWalletBalance(user.getWalletBalance() - tradeDto.getPrice() * tradeDto.getQuantity());
      if (tradeDto.getSymbol().equalsIgnoreCase("ETHUSDT")) {
        user.setEthBalance(user.getEthBalance() + tradeDto.getQuantity());
      } else if (tradeDto.getSymbol().equalsIgnoreCase("BTCUSDT")) {
        user.setBtcBalance(user.getBtcBalance() + tradeDto.getQuantity());
      }
    } else if (tradeDto.getTradeType().equalsIgnoreCase("SELL")) {
      user.setWalletBalance(user.getWalletBalance() + tradeDto.getPrice() * tradeDto.getQuantity());
      if (tradeDto.getSymbol().equalsIgnoreCase("ETHUSDT")) {
        user.setEthBalance(user.getEthBalance() - tradeDto.getQuantity());
      } else if (tradeDto.getSymbol().equalsIgnoreCase("BTCUSDT")) {
        user.setBtcBalance(user.getBtcBalance() - tradeDto.getQuantity());
      }
    }

    userRepository.save(user);
    return tradeMapper.toDto(trade);
  }

  public List<TradeDto> getTradeHistory(Long userId) {
    List<Trade> trades = tradeRepository.findByUserId(userId);
    return trades.stream()
        .map(tradeMapper::toDto)
        .collect(Collectors.toList());
  }
}

