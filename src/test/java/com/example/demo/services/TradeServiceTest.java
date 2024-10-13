package com.example.demo.services;

import com.example.demo.dtos.TradeDto;
import com.example.demo.entities.Trade;
import com.example.demo.entities.User;
import com.example.demo.mappers.TradeMapper;
import com.example.demo.repositories.TradeRepository;
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

class TradeServiceTest {

  @Mock
  private TradeRepository tradeRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private TradeMapper tradeMapper;

  @InjectMocks
  private TradeService tradeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void placeTrade_shouldReturnTradeDtoAndUpdateUserBalanceForBuy() {
    // Arrange
    TradeDto tradeDto = new TradeDto();
    tradeDto.setUserId(1L);
    tradeDto.setSymbol("ETHUSDT");
    tradeDto.setTradeType("BUY");
    tradeDto.setPrice(2000.0);
    tradeDto.setQuantity(1.0);

    User user = new User();
    user.setId(1L);
    user.setWalletBalance(5000.0);
    user.setEthBalance(0.0);

    Trade trade = new Trade();
    trade.setId(1L);

    when(userRepository.findById(tradeDto.getUserId())).thenReturn(Optional.of(user));
    when(tradeMapper.toEntity(tradeDto, user)).thenReturn(trade);
    when(tradeRepository.save(trade)).thenReturn(trade);
    when(tradeMapper.toDto(trade)).thenReturn(tradeDto);

    // Act
    TradeDto result = tradeService.placeTrade(tradeDto);

    // Assert
    assertEquals(tradeDto, result);
    assertEquals(3000.0, user.getWalletBalance());
    assertEquals(1.0, user.getEthBalance());
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void placeTrade_shouldReturnTradeDtoAndUpdateUserBalanceForSell() {
    // Arrange
    TradeDto tradeDto = new TradeDto();
    tradeDto.setUserId(1L);
    tradeDto.setSymbol("BTCUSDT");
    tradeDto.setTradeType("SELL");
    tradeDto.setPrice(30000.0);
    tradeDto.setQuantity(1.0);

    User user = new User();
    user.setId(1L);
    user.setWalletBalance(5000.0);
    user.setBtcBalance(1.0);

    Trade trade = new Trade();
    trade.setId(1L);

    when(userRepository.findById(tradeDto.getUserId())).thenReturn(Optional.of(user));
    when(tradeMapper.toEntity(tradeDto, user)).thenReturn(trade);
    when(tradeRepository.save(trade)).thenReturn(trade);
    when(tradeMapper.toDto(trade)).thenReturn(tradeDto);

    // Act
    TradeDto result = tradeService.placeTrade(tradeDto);

    // Assert
    assertEquals(tradeDto, result);
    assertEquals(35000.0, user.getWalletBalance());
    assertEquals(0.0, user.getBtcBalance());
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void placeTrade_shouldThrowExceptionWhenUserNotFound() {
    // Arrange
    TradeDto tradeDto = new TradeDto();
    tradeDto.setUserId(1L);

    when(userRepository.findById(tradeDto.getUserId())).thenReturn(Optional.empty());

    // Act & Assert
    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      tradeService.placeTrade(tradeDto);
    });

    assertEquals("User not found", thrown.getMessage());
    verify(userRepository, times(1)).findById(tradeDto.getUserId());
  }

  @Test
  void getTradeHistory_shouldReturnListOfTradeDtos() {
    // Arrange
    Long userId = 1L;
    List<Trade> trades = new ArrayList<>();
    Trade trade1 = new Trade();
    Trade trade2 = new Trade();
    trades.add(trade1);
    trades.add(trade2);

    TradeDto tradeDto1 = new TradeDto();
    TradeDto tradeDto2 = new TradeDto();

    when(tradeRepository.findByUserId(userId)).thenReturn(trades);
    when(tradeMapper.toDto(trade1)).thenReturn(tradeDto1);
    when(tradeMapper.toDto(trade2)).thenReturn(tradeDto2);

    // Act
    List<TradeDto> result = tradeService.getTradeHistory(userId);

    // Assert
    assertEquals(2, result.size());
    assertEquals(tradeDto1, result.get(0));
    assertEquals(tradeDto2, result.get(1));
    verify(tradeRepository, times(1)).findByUserId(userId);
  }
}
