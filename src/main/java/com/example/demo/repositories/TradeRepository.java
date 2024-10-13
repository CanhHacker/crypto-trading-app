package com.example.demo.repositories;

import com.example.demo.entities.Trade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

  List<Trade> findByUserId(Long userId);
}
