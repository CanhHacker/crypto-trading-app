package com.example.demo.repositories;

import com.example.demo.entities.TransactionHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

  List<TransactionHistory> findByUserId(Long userId);
}
