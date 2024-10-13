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
public class TradeDto {
  private Long id;
  private Long userId;
  private String symbol;
  private String tradeType;
  private Double price;
  private Double quantity;
  private LocalDateTime timestamp;
}
