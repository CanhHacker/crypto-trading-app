package com.example.demo.controllers;

import com.example.demo.dtos.CryptoPriceDto;
import com.example.demo.services.CryptoPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
public class CryptoPriceController {

  @Autowired
  private CryptoPriceService cryptoPriceService;

  @GetMapping("/latest/{symbol}")
  public ResponseEntity<CryptoPriceDto> getLatestPrice(@PathVariable String symbol) {
    return ResponseEntity.ok(cryptoPriceService.getLatestPrice(symbol));
  }

  @PostMapping("/save")
  public ResponseEntity<Void> saveCryptoPrice(@RequestBody CryptoPriceDto cryptoPriceDto) {
    cryptoPriceService.saveCryptoPrice(cryptoPriceDto);
    return ResponseEntity.ok().build();
  }
}

