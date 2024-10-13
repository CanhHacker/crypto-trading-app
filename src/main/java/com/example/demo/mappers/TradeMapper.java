package com.example.demo.mappers;

import com.example.demo.dtos.TradeDto;
import com.example.demo.entities.Trade;
import com.example.demo.entities.User;
import org.springframework.stereotype.Component;

@Component
public class TradeMapper {

  public TradeDto toDto(Trade trade) {
    TradeDto dto = new TradeDto();
    dto.setId(trade.getId());
    dto.setUserId(trade.getUser().getId());
    dto.setSymbol(trade.getSymbol());
    dto.setTradeType(trade.getTradeType());
    dto.setPrice(trade.getPrice());
    dto.setQuantity(trade.getQuantity());
    dto.setTimestamp(trade.getTimestamp());
    return dto;
  }

  public Trade toEntity(TradeDto dto, User user) {
    Trade trade = new Trade();
    trade.setId(dto.getId());
    trade.setUser(user);
    trade.setSymbol(dto.getSymbol());
    trade.setTradeType(dto.getTradeType());
    trade.setPrice(dto.getPrice());
    trade.setQuantity(dto.getQuantity());
    trade.setTimestamp(dto.getTimestamp());
    return trade;
  }
}
