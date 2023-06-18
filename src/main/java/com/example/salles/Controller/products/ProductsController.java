package com.example.salles.Controller.products;


import com.example.salles.Controller.products.service.ProductsService;
import com.example.salles.Entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductsController {
    private final ProductsService service;

    @Autowired
    public ProductsController(ProductsService service) {
        this.service = service;
    }

    @PostMapping("/company/{companyId}/category/{categoryId}")
    public ResponseEntity<Products> createProductsByCompany (@PathVariable UUID companyId, @PathVariable UUID categoryId, @RequestBody Products products) {
        return ResponseEntity.ok(service.createProductByCompany(companyId, products, categoryId));
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<Products>> getProductsByCompany (@PathVariable UUID id) {
        return ResponseEntity.ok(service.getAllProductByCompany(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductsById (@PathVariable UUID id) {
        return ResponseEntity.ok(service.getProductsById(id));
    }

    @GetMapping("/{companyId}/{categoryId}")
    public List<Products> getProductsByCompanyAndCategory(
            @PathVariable UUID companyId,
            @PathVariable UUID categoryId
    ) {
        return service.getProductsByCompanyAndCategory(companyId, categoryId);
    }

    @GetMapping("company/{companyId}/category/{categoryName}")
    public List<Products> getProductsByCompanyAndCategory(
            @PathVariable UUID companyId,
            @PathVariable String categoryName
    ) {
        return service.getProductsByCompanyAndCategoryByName(companyId, categoryName);
    }

    @GetMapping("company/{companyId}/product/{productId}")
    public ResponseEntity<Products> getProductsByIdByCompany (@PathVariable UUID companyId, @PathVariable UUID productId) {
        return ResponseEntity.ok(service.getByIdProductByCompany(companyId, productId));
    }

    @PutMapping("company/{companyId}/product/{productId}")
    public ResponseEntity<Products> updateProductByCompany (@PathVariable UUID companyId, @PathVariable UUID productId, @RequestBody Products products) {
        return ResponseEntity.ok(service.updateProductByCompany(companyId, productId, products));
    }

    @PutMapping("/{productId}")
    public Products updateProduct(@PathVariable UUID productId, @RequestBody Products updatedProduct) {
        return service.updateProduct(productId, updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductsByCompany (@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
