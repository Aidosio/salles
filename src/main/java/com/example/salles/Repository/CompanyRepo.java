package com.example.salles.Repository;

import com.example.salles.Entity.Company;
import com.example.salles.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepo extends JpaRepository<Company, UUID> {
    Optional<Company> findByName(String name);

    Optional<Company> findByOwnerId(UUID ownerId);

    Company findBySellersId(UUID sellerId);

    List<Company> findAllByOwnerOrSellersContaining(User owner, User seller);

}

