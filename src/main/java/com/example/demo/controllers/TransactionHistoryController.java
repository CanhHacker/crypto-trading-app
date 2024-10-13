package com.example.demo.controllers;
import com.example.demo.dtos.TransactionHistoryDto;
import com.example.demo.services.TransactionHistoryService;
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
@RequestMapping("/api/transactions")
public class TransactionHistoryController {

  @Autowired
  private TransactionHistoryService transactionHistoryService;

  @GetMapping("/history/{userId}")
  public ResponseEntity<List<TransactionHistoryDto>> getTransactionHistory(@PathVariable Long userId) {
    return ResponseEntity.ok(transactionHistoryService.getTransactionHistory(userId));
  }

  @PostMapping("/save")
  public ResponseEntity<Void> saveTransaction(@RequestBody TransactionHistoryDto transactionHistoryDto) {
    transactionHistoryService.saveTransaction(transactionHistoryDto);
    return ResponseEntity.ok().build();
  }
}

