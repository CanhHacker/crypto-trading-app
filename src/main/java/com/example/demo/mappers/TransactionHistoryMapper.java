package com.example.demo.mappers;
import com.example.demo.dtos.TransactionHistoryDto;
import com.example.demo.entities.Trade;
import com.example.demo.entities.TransactionHistory;
import com.example.demo.entities.User;
import org.springframework.stereotype.Component;

@Component
public class TransactionHistoryMapper {

  public TransactionHistoryDto toDto(TransactionHistory history) {
    TransactionHistoryDto dto = new TransactionHistoryDto();
    dto.setId(history.getId());
    dto.setUserId(history.getUser().getId());
    dto.setTradeId(history.getTrade().getId());
    dto.setBalanceChange(history.getBalanceChange());
    dto.setTimestamp(history.getTimestamp());
    return dto;
  }

  public TransactionHistory toEntity(TransactionHistoryDto dto, User user, Trade trade) {
    TransactionHistory history = new TransactionHistory();
    history.setId(dto.getId());
    history.setUser(user);
    history.setTrade(trade);
    history.setBalanceChange(dto.getBalanceChange());
    history.setTimestamp(dto.getTimestamp());
    return history;
  }
}

