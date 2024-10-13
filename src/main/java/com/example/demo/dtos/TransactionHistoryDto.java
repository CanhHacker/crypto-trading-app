package com.example.demo.dtos;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TransactionHistoryDto {
  private Long id;
  private Long userId;
  private Long tradeId;
  private Double balanceChange;
  private LocalDateTime timestamp;
}
