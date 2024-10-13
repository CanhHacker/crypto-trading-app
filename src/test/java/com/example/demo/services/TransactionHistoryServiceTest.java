package com.example.demo.services;

import com.example.demo.dtos.TransactionHistoryDto;
import com.example.demo.entities.Trade;
import com.example.demo.entities.TransactionHistory;
import com.example.demo.entities.User;
import com.example.demo.mappers.TransactionHistoryMapper;
import com.example.demo.repositories.TradeRepository;
import com.example.demo.repositories.TransactionHistoryRepository;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionHistoryServiceTest {

  @Mock
  private TransactionHistoryRepository transactionHistoryRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private TradeRepository tradeRepository;

  @Mock
  private TransactionHistoryMapper transactionHistoryMapper;

  @InjectMocks
  private TransactionHistoryService transactionHistoryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getTransactionHistory_shouldReturnListOfTransactionHistoryDtos() {
    // Arrange
    Long userId = 1L;
    List<TransactionHistory> transactionHistories = new ArrayList<>();
    TransactionHistory transactionHistory1 = new TransactionHistory();
    TransactionHistory transactionHistory2 = new TransactionHistory();
    transactionHistories.add(transactionHistory1);
    transactionHistories.add(transactionHistory2);

    TransactionHistoryDto transactionHistoryDto1 = new TransactionHistoryDto();
    TransactionHistoryDto transactionHistoryDto2 = new TransactionHistoryDto();

    when(transactionHistoryRepository.findByUserId(userId)).thenReturn(transactionHistories);
    when(transactionHistoryMapper.toDto(transactionHistory1)).thenReturn(transactionHistoryDto1);
    when(transactionHistoryMapper.toDto(transactionHistory2)).thenReturn(transactionHistoryDto2);

    // Act
    List<TransactionHistoryDto> result = transactionHistoryService.getTransactionHistory(userId);

    // Assert
    assertEquals(2, result.size());
    assertEquals(transactionHistoryDto1, result.get(0));
    assertEquals(transactionHistoryDto2, result.get(1));
    verify(transactionHistoryRepository, times(1)).findByUserId(userId);
  }

  @Test
  void saveTransaction_shouldSaveTransactionHistory() {
    // Arrange
    TransactionHistoryDto dto = new TransactionHistoryDto();
    dto.setUserId(1L);
    dto.setTradeId(1L);

    User user = new User();
    user.setId(1L);

    Trade trade = new Trade();
    trade.setId(1L);

    TransactionHistory transactionHistory = new TransactionHistory();

    when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
    when(tradeRepository.findById(dto.getTradeId())).thenReturn(Optional.of(trade));
    when(transactionHistoryMapper.toEntity(dto, user, trade)).thenReturn(transactionHistory);

    // Act
    transactionHistoryService.saveTransaction(dto);

    // Assert
    verify(transactionHistoryRepository, times(1)).save(transactionHistory);
  }

  @Test
  void saveTransaction_shouldThrowExceptionWhenUserNotFound() {
    // Arrange
    TransactionHistoryDto dto = new TransactionHistoryDto();
    dto.setUserId(1L);
    dto.setTradeId(1L);

    when(userRepository.findById(dto.getUserId())).thenReturn(Optional.empty());

    // Act & Assert
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      transactionHistoryService.saveTransaction(dto);
    });

    assertEquals("User not found", thrown.getMessage());
    verify(transactionHistoryRepository, times(0)).save(any());
  }

  @Test
  void saveTransaction_shouldThrowExceptionWhenTradeNotFound() {
    // Arrange
    TransactionHistoryDto dto = new TransactionHistoryDto();
    dto.setUserId(1L);
    dto.setTradeId(1L);

    User user = new User();
    user.setId(1L);

    when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
    when(tradeRepository.findById(dto.getTradeId())).thenReturn(Optional.empty());

    // Act & Assert
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      transactionHistoryService.saveTransaction(dto);
    });

    assertEquals("Trade not found", thrown.getMessage());
    verify(transactionHistoryRepository, times(0)).save(any());
  }
}
