package com.example.salles.Repository;

import com.example.salles.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepo extends JpaRepository<Company, UUID> {
    Optional<Company> findByName(String name);
}

