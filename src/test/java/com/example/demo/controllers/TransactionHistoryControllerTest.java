package com.example.demo.controllers;

import com.example.demo.dtos.TransactionHistoryDto;
import com.example.demo.services.TransactionHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionHistoryControllerTest {

  @Mock
  private TransactionHistoryService transactionHistoryService;

  @InjectMocks
  private TransactionHistoryController transactionHistoryController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getTransactionHistory_shouldReturnTransactionHistory() {
    // Arrange
    Long userId = 1L;
    TransactionHistoryDto transaction1 = new TransactionHistoryDto();
    transaction1.setUserId(userId);
    transaction1.setBalanceChange(100.0);

    TransactionHistoryDto transaction2 = new TransactionHistoryDto();
    transaction2.setUserId(userId);
    transaction2.setBalanceChange(-50.0);

    List<TransactionHistoryDto> transactionHistory = Arrays.asList(transaction1, transaction2);

    when(transactionHistoryService.getTransactionHistory(userId)).thenReturn(transactionHistory);

    // Act
    ResponseEntity<List<TransactionHistoryDto>> response = transactionHistoryController.getTransactionHistory(userId);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(transactionHistory, response.getBody());
    verify(transactionHistoryService, times(1)).getTransactionHistory(userId);
  }

  @Test
  void saveTransaction_shouldReturnOk() {
    // Arrange
    TransactionHistoryDto transactionHistoryDto = new TransactionHistoryDto();
    transactionHistoryDto.setUserId(1L);
    transactionHistoryDto.setBalanceChange(150.0);

    ResponseEntity<Void> response = transactionHistoryController.saveTransaction(transactionHistoryDto);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(transactionHistoryService, times(1)).saveTransaction(transactionHistoryDto);
  }
}
