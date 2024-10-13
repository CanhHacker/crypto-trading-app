package com.example.demo.controllers;

import com.example.demo.dtos.CryptoPriceDto;
import com.example.demo.services.CryptoPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CryptoPriceControllerTest {

  @Mock
  private CryptoPriceService cryptoPriceService;

  @InjectMocks
  private CryptoPriceController cryptoPriceController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getLatestPrice_shouldReturnPrice() {
    String symbol = "BTCUSDT";
    CryptoPriceDto cryptoPriceDto = new CryptoPriceDto();
    cryptoPriceDto.setSymbol(symbol);
    cryptoPriceDto.setAskPrice(1800.00);

    when(cryptoPriceService.getLatestPrice(symbol)).thenReturn(cryptoPriceDto);

    ResponseEntity<CryptoPriceDto> response = cryptoPriceController.getLatestPrice(symbol);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(cryptoPriceDto, response.getBody());
    verify(cryptoPriceService, times(1)).getLatestPrice(symbol);
  }

  @Test
  void saveCryptoPrice_shouldReturnOk() {
    // Arrange
    CryptoPriceDto cryptoPriceDto = new CryptoPriceDto();
    cryptoPriceDto.setSymbol("BTCUSDT");
    cryptoPriceDto.setAskPrice(50000.00);

    // Act
    ResponseEntity<Void> response = cryptoPriceController.saveCryptoPrice(cryptoPriceDto);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(cryptoPriceService, times(1)).saveCryptoPrice(cryptoPriceDto);
  }
}
