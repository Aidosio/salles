package com.example.salles.Controller.purchases;


import com.example.salles.Controller.purchases.service.PurchasesService;
import com.example.salles.Entity.Purchase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchasesController {
    private final PurchasesService purchasesService;

    public PurchasesController(PurchasesService purchasesService) {
        this.purchasesService = purchasesService;
    }

    @PostMapping("/company/{id}")
    public ResponseEntity<Purchase> createPurchase(@PathVariable UUID id, @RequestBody Purchase purchase) {
        return ResponseEntity.ok(purchasesService.create(id, purchase));
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<Purchase>> findAllPurchase(@PathVariable UUID id) {
        return ResponseEntity.ok(purchasesService.findAll(id));
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> findById(@PathVariable UUID purchaseId) {
        return ResponseEntity.ok(purchasesService.findById(purchaseId));
    }

//    @PostMapping("/{purchaseId}/add")
//    public Purchase addProduct(@PathVariable UUID purchaseId, @RequestBody PurchaseDto purchaseDto) {
//        List<Products> products = purchaseDto.getProducts().stream()
//                .map(p -> new Products(p.getId()))
//                .collect(Collectors.toList());
//        return purchasesService.addProducts(purchaseId, products);
//    }

//    @PostMapping("/{purchaseId}/add")
//    public Purchase addProductToPurchase(@PathVariable UUID purchaseId, @RequestBody Map<String, List<UUID>> productIdsMap) {
//        List<UUID> productIds = productIdsMap.get("productIds");
//        return purchasesService.addProductId(purchaseId, productIds);
//    }
//
//    @DeleteMapping("/{purchaseId}/products/{productId}")
//    public ResponseEntity<Purchase> removeProductFromPurchase(@PathVariable UUID purchaseId, @PathVariable UUID productId) {
//        Purchase purchase = purchasesService.removeProductIdFromPurchase(purchaseId, productId);
//        return ResponseEntity.ok(purchase);
//    }

//    @PostMapping("/{purchaseId}/close")
//    public ResponseEntity<Purchase> closeStatus(@PathVariable UUID purchaseId) {
//        return ResponseEntity.ok(purchasesService.closeStatus(purchaseId));

//    }
//    @PostMapping("/{purchaseId}/total")
//    public ResponseEntity<Purchase> totalPrice(@PathVariable UUID purchaseId, @RequestBody Purchase purchase) {
//        return ResponseEntity.ok(purchasesService.totalPrice(purchaseId, purchase));

//    }

//    @PostMapping("/company/{companyId}/purchase/{purchaseId}/delete/product/{productId}")
//    public ResponseEntity<Optional<Purchase>> deleteProduct(@PathVariable UUID companyId, @PathVariable UUID purchaseId, @PathVariable UUID productId) {
//        return ResponseEntity.ok(purchasesService.deleteProduct(companyId, purchaseId, productId));
//    }

//    @PostMapping("/{purchaseId}/delete/product/{productId}")
//    public ResponseEntity<Purchase> deleteProduct(@PathVariable UUID purchaseId, @PathVariable UUID productId) {
//        return ResponseEntity.ok(purchasesService.removeProductFromPurchase(purchaseId, productId));
//    }

//    @PostMapping("/{purchaseId}/return/product/{productId}")
//    public ResponseEntity<Optional<Purchase>> addReturnProduct(@PathVariable UUID purchaseId, @PathVariable UUID productId) {
//        return ResponseEntity.ok(purchasesService.addReturnProduct(purchaseId, productId));
//    }
}