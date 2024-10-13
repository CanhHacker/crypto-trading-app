package com.example.demo.mappers;

import com.example.demo.dtos.CryptoPriceDto;
import com.example.demo.entities.CryptoPrice;
import org.springframework.stereotype.Component;

@Component
public class CryptoPriceMapper {

  public CryptoPriceDto toDto(CryptoPrice cryptoPrice) {
    CryptoPriceDto dto = new CryptoPriceDto();
    dto.setId(cryptoPrice.getId());
    dto.setSymbol(cryptoPrice.getSymbol());
    dto.setBidPrice(cryptoPrice.getBidPrice());
    dto.setAskPrice(cryptoPrice.getAskPrice());
    dto.setSource(cryptoPrice.getSource());
    dto.setTimestamp(cryptoPrice.getTimestamp());
    return dto;
  }

  public CryptoPrice toEntity(CryptoPriceDto dto) {
    CryptoPrice cryptoPrice = new CryptoPrice();
    cryptoPrice.setId(dto.getId());
    cryptoPrice.setSymbol(dto.getSymbol());
    cryptoPrice.setBidPrice(dto.getBidPrice());
    cryptoPrice.setAskPrice(dto.getAskPrice());
    cryptoPrice.setSource(dto.getSource());
    cryptoPrice.setTimestamp(dto.getTimestamp());
    return cryptoPrice;
  }
}
