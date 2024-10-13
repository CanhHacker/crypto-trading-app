package com.example.demo.controllers;

import com.example.demo.dtos.TradeDto;
import com.example.demo.services.TradeService;
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

class TradeControllerTest {

  @Mock
  private TradeService tradeService;

  @InjectMocks
  private TradeController tradeController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void placeTrade_shouldReturnPlacedTrade() {
    TradeDto tradeDto = new TradeDto();
    tradeDto.setUserId(1L);
    tradeDto.setSymbol("ETHUSDT");
    tradeDto.setTradeType("BUY");
    tradeDto.setQuantity(1.0);
    tradeDto.setPrice(1800.0);

    when(tradeService.placeTrade(tradeDto)).thenReturn(tradeDto);
    ResponseEntity<TradeDto> response = tradeController.placeTrade(tradeDto);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(tradeDto, response.getBody());
    verify(tradeService, times(1)).placeTrade(tradeDto);
  }

  @Test
  void getTradeHistory_shouldReturnTradeHistory() {
    Long userId = 1L;
    TradeDto trade1 = new TradeDto();
    trade1.setUserId(userId);
    trade1.setSymbol("ETHUSDT");
    trade1.setTradeType("BUY");
    trade1.setQuantity(2.0);
    trade1.setPrice(1800.0);

    TradeDto trade2 = new TradeDto();
    trade2.setUserId(userId);
    trade2.setSymbol("BTCUSDT");
    trade2.setTradeType("SELL");
    trade2.setQuantity(1.0);
    trade2.setPrice(50000.0);

    List<TradeDto> tradeHistory = Arrays.asList(trade1, trade2);

    when(tradeService.getTradeHistory(userId)).thenReturn(tradeHistory);

    ResponseEntity<List<TradeDto>> response = tradeController.getTradeHistory(userId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(tradeHistory, response.getBody());
    verify(tradeService, times(1)).getTradeHistory(userId);
  }
}
