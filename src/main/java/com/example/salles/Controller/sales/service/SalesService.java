package com.example.salles.Controller.sales.service;

import com.example.salles.Entity.*;
import com.example.salles.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesService {

    private final CompanyRepo companyRepository;
    private final ProductsRepo productsRepository;
    private final PurchaseRepo purchaseRepository;

    @Autowired
    public SalesService(
            CompanyRepo companyRepository,
            ProductsRepo productsRepository,
            PurchaseRepo purchaseRepository
    ) {
        this.companyRepository = companyRepository;
        this.productsRepository = productsRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase createSale(UUID companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            Purchase sale = new Purchase();
            sale.setCompany(company);
            purchaseRepository.save(sale);
            return sale;
        }
        throw new IllegalArgumentException("Company not found");
    }

    public Purchase getSaleById(UUID saleId) {
        return purchaseRepository.findById(saleId).orElseThrow(() -> new IllegalArgumentException("Sale not found"));
    }

    public List<Purchase> getAllSales() {
        return purchaseRepository.findAll();
    }

    public Purchase addProductToSale(UUID saleId, UUID productId, int quantity) {
        Purchase sale = getSaleById(saleId);
        Optional<Products> productOptional = productsRepository.findById(productId);
        if (productOptional.isPresent()) {
            Products product = productOptional.get();
            PurchaseItem item = new PurchaseItem(productId, quantity);
            sale.getProductIds().add(item);
            purchaseRepository.save(sale);
            int remainingQuantity = product.getCount() - quantity;
            product.setCount(remainingQuantity);
            productsRepository.save(product);
            return sale;
        }
        throw new IllegalArgumentException("Product not found");
    }

    public Purchase removeProductFromSale(UUID saleId, UUID productId) {
        Purchase sale = getSaleById(saleId);
        List<PurchaseItem> items = sale.getProductIds();
        Optional<Products> productOptional = productsRepository.findById(productId);
        if (productOptional.isPresent()) {
            Products product = productOptional.get();
            for (PurchaseItem item : items) {
                if (item.getProductId().equals(productId)) {
                    int quantity = item.getQuantity();
                    items.remove(item);
                    purchaseRepository.save(sale);
                    int remainingQuantity = product.getCount() + quantity;
                    product.setCount(remainingQuantity);
                    productsRepository.save(product);
                    return sale;
                }
            }
        }
        throw new IllegalArgumentException("Product not found");
    }

    public Purchase returnProduct(UUID saleId, UUID productId, int quantity) {
        Purchase sale = getSaleById(saleId);
        Optional<Products> productOptional = productsRepository.findById(productId);
        if (productOptional.isPresent()) {
            Products product = productOptional.get();
            ReturnProduct returnProduct = new ReturnProduct(productId, quantity);
            sale.getReturnProducts().add(returnProduct);
            purchaseRepository.save(sale);
            int remainingQuantity = product.getCount() + quantity;
            product.setCount(remainingQuantity); // Возвращаем продукты на склад
            productsRepository.save(product);
            List<PurchaseItem> items = sale.getProductIds();
            for (PurchaseItem item : items) {
                if (item.getProductId().equals(productId)) {
                    int updatedQuantity = item.getQuantity() - quantity;
                    item.setQuantity(updatedQuantity); // Обновляем количество продукта
                    purchaseRepository.save(sale);
                    return sale;
                }
            }
        }
        throw new IllegalArgumentException("Product not found");
    }


    public Purchase setSaleStatus(UUID saleId, boolean status) {
        Purchase sale = getSaleById(saleId);
        sale.setStatus(status);
        purchaseRepository.save(sale);
        return sale;
    }

    public double calculateTotalPrice(UUID saleId) {
        Purchase sale = getSaleById(saleId);
        double totalPrice = 0.0;
        List<PurchaseItem> items = sale.getProductIds();
        for (PurchaseItem item : items) {
            Optional<Products> productOptional = productsRepository.findById(item.getProductId());
            if (productOptional.isPresent()) {
                Products product = productOptional.get();
                double itemPrice = product.getPrice() * item.getQuantity();
                totalPrice += itemPrice;
            }
        }
        sale.setPrice(totalPrice); // Записываем общую цену в поле price
        purchaseRepository.save(sale); // Сохраняем изменения в базе данных
        return totalPrice;
    }

}

