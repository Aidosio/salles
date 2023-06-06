package com.example.salles.Repository;

import com.example.salles.Entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseRepo extends JpaRepository<Purchase, UUID> {
    Page<Purchase> findTop4ByOrderByCreateDateDesc(Pageable pageable);
}
