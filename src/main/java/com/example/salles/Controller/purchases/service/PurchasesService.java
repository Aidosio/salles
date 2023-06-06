package com.example.salles.Controller.purchases.service;


import com.example.salles.Entity.Company;
import com.example.salles.Entity.Purchase;
import com.example.salles.Repository.CompanyRepo;
import com.example.salles.Repository.ProductsRepo;
import com.example.salles.Repository.PurchaseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PurchasesService {

    private final PurchaseRepo purchasesRepository;
    private final CompanyRepo companyRepo;
    private final ProductsRepo productRepository;

    public PurchasesService(PurchaseRepo purchasesRepository, CompanyRepo companyRepo, ProductsRepo productRepository) {
        this.purchasesRepository = purchasesRepository;
        this.companyRepo = companyRepo;
        this.productRepository = productRepository;
    }

    public Purchase create(UUID companyId, Purchase purchase) {
        Company company = companyRepo.findById(companyId).orElse(null);
        purchase.setCompany(company);
        return purchasesRepository.save(purchase);
    }

    public List<Purchase> findAll(UUID companyId) {
        Company company = companyRepo.findById(companyId).orElse(null);
        return Objects.requireNonNull(company).getPurchases();
    }

    public Purchase findById(UUID id) {
        return purchasesRepository.findById(id).orElse(null);
    }

//    public Purchase addProducts(UUID purchaseId, List<Products> products) {
//        Purchase purchase = purchasesRepository.findById(purchaseId)
//                .orElseThrow(() -> new RuntimeException("Purchase not found"));
//        purchase.getProducts().addAll(products);
//        return purchasesRepository.save(purchase);
//    }

//    public Purchase addProduct(UUID purchaseId, List<UUID> productIds) {
//        Purchase purchase = purchasesRepository.findById(purchaseId)
//                .orElseThrow(() -> new EntityNotFoundException("Purchase not found"));
//        purchase.getProductIds().addAll(productIds);
//        return purchasesRepository.save(purchase);
//    }

//    public Purchase addProductId(UUID purchaseId, List<UUID> productIds) {
//        Purchase purchase = purchasesRepository.findById(purchaseId).orElse(null);
//        if (purchase == null) {
//            throw new IllegalArgumentException("Purchase not found for id: " + purchaseId);
//        }
//        purchase.getProductIds().addAll(productIds);
//        return purchasesRepository.save(purchase);
//    }
//
//
//    public Purchase removeProductIdFromPurchase(UUID purchaseId, UUID productId) {
//        Purchase purchase = purchasesRepository.findById(purchaseId)
//                .orElseThrow(() -> new EntityNotFoundException("Purchase not found with id " + purchaseId));
//
//        List<UUID> productIds = purchase.getProductIds();
//        if (!productIds.contains(productId)) {
//            throw new IllegalArgumentException("Product with id " + productId + " is not present in purchase with id " + purchaseId);
//        }
//        productIds.remove(productId);
//
//        return purchasesRepository.save(purchase);
//    }


//    public Purchase create(UUID companyId, Purchase purchase) {
//        Company company = companyRepo.findById(companyId).orElse(null);
//        purchase.setCompany(company);
//        return purchasesRepository.save(purchase);
//    }
//
//    public List<Purchase> findAll(UUID companyId) {
//        Company company = companyRepo.findById(companyId).orElse(null);
//        return company.getPurchases();
//    }
//
//    public Purchase findById(UUID companyId, UUID purchaseId) {
//        Company company = companyRepo.findById(companyId).orElse(null);
//        return company.getPurchases().stream().filter(purchase -> purchase.getId().equals(purchaseId)).findFirst().orElse(null);
//    }
//
//    public Optional<Purchase> addProduct(UUID companyId, UUID purchaseId, UUID productId) {
//        Optional<Company> optionalCompany = companyRepo.findById(companyId);
//        if (optionalCompany.isPresent()) {
//            Company company = optionalCompany.get();
//            Optional<Products> optionalProduct = company.getProducts()
//                    .stream()
//                    .filter(p -> p.getId().equals(productId))
//                    .findFirst();
//            Optional<Purchase> optionalPurchase = company.getPurchases()
//                    .stream()
//                    .filter(p -> p.getId().equals(purchaseId))
//                    .findFirst();
//            if (optionalProduct.isPresent() && optionalPurchase.isPresent()) {
//                Purchase purchase = optionalPurchase.get();
//                Products product = optionalProduct.get();
//                purchase.getProducts().add(product);
//                purchasesRepository.save(purchase);
//                return Optional.of(purchase);
//            }
//        }
//        return Optional.empty();
//    }
//
//    public Optional<Purchase> addReturnProduct(UUID companyId, UUID purchaseId, UUID productId) {
//        Optional<Company> optionalCompany = companyRepo.findById(companyId);
//        if (optionalCompany.isPresent()) {
//            Company company = optionalCompany.get();
//            Optional<Products> optionalProduct = company.getProducts()
//                    .stream()
//                    .filter(p -> p.getId().equals(productId))
//                    .findFirst();
//            Optional<Purchase> optionalPurchase = company.getPurchases()
//                    .stream()
//                    .filter(p -> p.getId().equals(purchaseId))
//                    .findFirst();
//            if (optionalProduct.isPresent() && optionalPurchase.isPresent()) {
//                Purchase purchase = optionalPurchase.get();
//                Products product = optionalProduct.get();
//                purchase.getReturnProducts().add(product);
//                purchasesRepository.save(purchase);
//                return Optional.of(purchase);
//            }
//        }
//        return Optional.empty();
//    }
//
//    public Purchase closeStatus(UUID purchaseId) {
//        var as = purchaseId;
//
//        Purchase purchase.setStatus(true);
//        return purchasesRepository.save(purchase);
//    }
//    public Purchase totalPrice(UUID companyId, UUID purchaseId, Purchase purchase) {
//        Company company = companyRepo.findById(companyId).orElse(null);
//        Purchase purchases = company.getPurchases().stream().filter(p -> p.getId().equals(purchaseId)).findFirst().orElse(null);
//        purchases.setPrice(purchase.getPrice());
//        return purchasesRepository.save(purchases);
//    }

}
