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
public class CryptoPriceDto {
  private Long id;
  private String symbol;
  private Double bidPrice;
  private Double askPrice;
  private String source;
  private LocalDateTime timestamp;
}