package com.example.salles.Repository;

import com.example.salles.Entity.CategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepo extends JpaRepository<CategoryList, UUID> {
    CategoryList findByName(String name);
}
