package com.example.salles.Controller.products.service;



import com.example.salles.Entity.CategoryList;
import com.example.salles.Entity.Company;
import com.example.salles.Entity.Products;
import com.example.salles.Repository.CategoryRepo;
import com.example.salles.Repository.CompanyRepo;
import com.example.salles.Repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductsService {
    private final ProductsRepo repo;
    private final CompanyRepo companyRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public ProductsService(ProductsRepo repo, CompanyRepo companyRepo, CategoryRepo categoryRepo) {
        this.repo = repo;
        this.companyRepo = companyRepo;
        this.categoryRepo = categoryRepo;
    }

    public Products createProductByCompany(UUID companyId, Products products, UUID categoryId) {
        Company company = companyRepo.findById(companyId).orElse(null);
        CategoryList category = categoryRepo.findById(categoryId).orElse(null);
        products.setCompany(company);
        products.setCategory(category);
        return repo.save(products);
    }


    public Products getProductsById(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Products> getAllProductByCompany(UUID companyId) {
        Company company = companyRepo.findById(companyId).orElse(null);
        return company.getProducts();
    }

    public List<Products> getAllCategoryProductByCompany(UUID companyId, String categoryName) {
        List<Products> products = getAllProductByCompany(companyId);
        return products.stream().filter(p -> p.getCategory().equals(categoryName)).toList();
    }

    public Products getByIdProductByCompany(UUID companyId, UUID productId) {
        Company company = companyRepo.findById(companyId).orElse(null);

        return company.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Products updateProductByCompany(UUID companyId, UUID productId, Products updatedProduct) {
        Products existingProduct = getByIdProductByCompany(companyId, productId);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCount(updatedProduct.getCount());
        existingProduct.setCategory(updatedProduct.getCategory());
        return repo.save(existingProduct);
    }

    public void deleteById(UUID id) {
        repo.deleteById(id);
    }
}
