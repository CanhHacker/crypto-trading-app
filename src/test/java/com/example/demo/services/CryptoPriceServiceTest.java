package com.example.demo.services;

import com.example.demo.dtos.CryptoPriceDto;
import com.example.demo.entities.CryptoPrice;
import com.example.demo.mappers.CryptoPriceMapper;
import com.example.demo.repositories.CryptoPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CryptoPriceServiceTest {

  @Mock
  private CryptoPriceRepository cryptoPriceRepository;

  @Mock
  private CryptoPriceMapper cryptoPriceMapper;

  @InjectMocks
  private CryptoPriceService cryptoPriceService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getLatestPrice_shouldReturnCryptoPriceDto() {
    // Arrange
    String symbol = "ETHUSDT";
    CryptoPrice cryptoPrice = new CryptoPrice();
    cryptoPrice.setSymbol(symbol);
    cryptoPrice.setAskPrice(2000.0);

    CryptoPriceDto cryptoPriceDto = new CryptoPriceDto();
    cryptoPriceDto.setSymbol(symbol);
    cryptoPriceDto.setAskPrice(2000.0);

    when(cryptoPriceRepository.findTopBySymbolOrderByTimestampDesc(symbol)).thenReturn(cryptoPrice);
    when(cryptoPriceMapper.toDto(cryptoPrice)).thenReturn(cryptoPriceDto);

    // Act
    CryptoPriceDto result = cryptoPriceService.getLatestPrice(symbol);

    // Assert
    assertEquals(cryptoPriceDto, result);
    verify(cryptoPriceRepository, times(1)).findTopBySymbolOrderByTimestampDesc(symbol);
    verify(cryptoPriceMapper, times(1)).toDto(cryptoPrice);
  }

  @Test
  void getLatestPrice_shouldThrowExceptionWhenPriceNotFound() {
    // Arrange
    String symbol = "ETHUSDT";

    when(cryptoPriceRepository.findTopBySymbolOrderByTimestampDesc(symbol)).thenReturn(null);

    // Act & Assert
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      cryptoPriceService.getLatestPrice(symbol);
    });

    assertEquals("Price not found for symbol: " + symbol, thrown.getMessage());
    verify(cryptoPriceRepository, times(1)).findTopBySymbolOrderByTimestampDesc(symbol);
  }

  @Test
  void saveCryptoPrice_shouldSaveCryptoPrice() {
    // Arrange
    CryptoPriceDto cryptoPriceDto = new CryptoPriceDto();
    cryptoPriceDto.setSymbol("BTCUSDT");
    cryptoPriceDto.setAskPrice(30000.0);

    CryptoPrice cryptoPrice = new CryptoPrice();
    cryptoPrice.setSymbol("BTCUSDT");
    cryptoPrice.setAskPrice(30000.0);

    when(cryptoPriceMapper.toEntity(cryptoPriceDto)).thenReturn(cryptoPrice);

    // Act
    cryptoPriceService.saveCryptoPrice(cryptoPriceDto);

    // Assert
    verify(cryptoPriceMapper, times(1)).toEntity(cryptoPriceDto);
    verify(cryptoPriceRepository, times(1)).save(cryptoPrice);
  }
}
