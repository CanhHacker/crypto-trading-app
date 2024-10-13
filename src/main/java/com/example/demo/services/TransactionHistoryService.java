package com.example.demo.services;

import com.example.demo.dtos.TransactionHistoryDto;
import com.example.demo.entities.Trade;
import com.example.demo.entities.TransactionHistory;
import com.example.demo.entities.User;
import com.example.demo.mappers.TransactionHistoryMapper;
import com.example.demo.repositories.TradeRepository;
import com.example.demo.repositories.TransactionHistoryRepository;
import com.example.demo.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryService {

  @Autowired
  private TransactionHistoryRepository transactionHistoryRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TradeRepository tradeRepository;

  @Autowired
  private TransactionHistoryMapper transactionHistoryMapper;

  public List<TransactionHistoryDto> getTransactionHistory(Long userId) {
    List<TransactionHistory> history = transactionHistoryRepository.findByUserId(userId);
    return history.stream()
        .map(transactionHistoryMapper::toDto)
        .collect(Collectors.toList());
  }

  public void saveTransaction(TransactionHistoryDto dto) {
    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));
    Trade trade = tradeRepository.findById(dto.getTradeId())
        .orElseThrow(() -> new RuntimeException("Trade not found"));
    TransactionHistory transactionHistory = transactionHistoryMapper.toEntity(dto, user, trade);
    transactionHistoryRepository.save(transactionHistory);
  }
}
