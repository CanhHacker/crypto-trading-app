package com.example.demo.controllers;
import com.example.demo.dtos.TradeDto;
import com.example.demo.services.TradeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

  @Autowired
  private TradeService tradeService;

  @PostMapping("/place")
  public ResponseEntity<TradeDto> placeTrade(@RequestBody TradeDto tradeDto) {
    return ResponseEntity.ok(tradeService.placeTrade(tradeDto));
  }

  @GetMapping("/history/{userId}")
  public ResponseEntity<List<TradeDto>> getTradeHistory(@PathVariable Long userId) {
    return ResponseEntity.ok(tradeService.getTradeHistory(userId));
  }
}