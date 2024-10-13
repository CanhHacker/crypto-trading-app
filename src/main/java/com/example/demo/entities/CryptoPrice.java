package com.example.demo.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "crypto_price")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CryptoPrice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "symbol")
  private String symbol;

  @Column(name = "bid_price")
  private Double bidPrice;

  @Column(name = "ask_price")
  private Double askPrice;

  @Column(name = "source")
  private String source;

  @Column(name = "timestamp")
  private LocalDateTime timestamp;
}