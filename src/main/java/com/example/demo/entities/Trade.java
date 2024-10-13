package com.example.demo.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "trade")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Trade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "symbol")
  private String symbol;

  @Column(name = "trade_type")
  private String tradeType; // BUY or SELL

  @Column(name = "price")
  private Double price;

  @Column(name = "quantity")
  private Double quantity;

  @Column(name = "timestamp")
  private LocalDateTime timestamp;
}