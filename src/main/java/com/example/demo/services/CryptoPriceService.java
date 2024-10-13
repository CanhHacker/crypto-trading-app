package com.example.demo.services;

import com.example.demo.dtos.CryptoPriceDto;
import com.example.demo.entities.CryptoPrice;
import com.example.demo.mappers.CryptoPriceMapper;
import com.example.demo.repositories.CryptoPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoPriceService {

  @Autowired
  private CryptoPriceRepository cryptoPriceRepository;

  @Autowired
  private CryptoPriceMapper cryptoPriceMapper;

  public CryptoPriceDto getLatestPrice(String symbol) {
    CryptoPrice cryptoPrice = cryptoPriceRepository.findTopBySymbolOrderByTimestampDesc(symbol);
    if (cryptoPrice == null) {
      throw new RuntimeException("Price not found for symbol: " + symbol);
    }
    return cryptoPriceMapper.toDto(cryptoPrice);
  }

  public void saveCryptoPrice(CryptoPriceDto cryptoPriceDto) {
    CryptoPrice cryptoPrice = cryptoPriceMapper.toEntity(cryptoPriceDto);
    cryptoPriceRepository.save(cryptoPrice);
  }
}

