package com.example.salles.Repository;

import com.example.salles.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductsRepo extends JpaRepository<Products, UUID> {
    List<Products> findByCompany_IdAndCategory_Id(UUID companyId, UUID categoryId);

}
