package com.example.salles.Repository;

import com.example.salles.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductsRepo extends JpaRepository<Products, UUID> {
    List<Products> findByCompany_IdAndCategory_Id(UUID companyId, UUID categoryId);
    List<Products> findByCompany_IdAndCategory_Name(UUID companyId, String categoryName);

    List<Products> findTop4ByCompanyIdOrderByCreatedDateDesc(UUID companyId);

    Products findByBarcode(String barcode);
}
