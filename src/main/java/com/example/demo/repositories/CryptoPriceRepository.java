package com.example.demo.repositories;

import com.example.demo.entities.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {

  CryptoPrice findTopBySymbolOrderByTimestampDesc(String symbol);
}
