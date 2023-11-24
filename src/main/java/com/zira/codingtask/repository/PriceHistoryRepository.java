package com.zira.codingtask.repository;

import com.zira.codingtask.repository.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {

    @Query(value = "select * from price_history p where productid = ?1", nativeQuery = true)
    List<PriceHistory> findPriceForProductHistory(Long id);
}
