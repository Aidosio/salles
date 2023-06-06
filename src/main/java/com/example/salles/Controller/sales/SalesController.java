package com.example.salles.Controller.sales;

import com.example.salles.Controller.sales.service.SalesService;
import com.example.salles.Entity.Company;
import com.example.salles.Entity.Products;
import com.example.salles.Entity.Purchase;
import com.example.salles.Entity.PurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/create")
    public Purchase createSale(@RequestParam UUID companyId) {
        return salesService.createSale(companyId);
    }

    @GetMapping("/get/{saleId}")
    public Purchase getSaleById(@PathVariable UUID saleId) {
        return salesService.getSaleById(saleId);
    }

    @GetMapping("/get")
    public List<Purchase> getAllSales() {
        return salesService.getAllSales();
    }

    @PutMapping("/add-product/{saleId}")
    public Purchase addProductToSale(
            @PathVariable UUID saleId,
            @RequestParam UUID productId,
            @RequestParam int quantity
    ) {
        return salesService.addProductToSale(saleId, productId, quantity);
    }

    @PutMapping("/remove-product/{saleId}")
    public Purchase removeProductFromSale(
            @PathVariable UUID saleId,
            @RequestParam UUID productId
    ) {
        return salesService.removeProductFromSale(saleId, productId);
    }

    @PutMapping("/return-product/{saleId}")
    public Purchase returnProduct(
            @PathVariable UUID saleId,
            @RequestParam UUID productId,
            @RequestParam int quantity
    ) {
        return salesService.returnProduct(saleId, productId, quantity);
    }

    @PostMapping("/set-status/{saleId}")
    public Purchase setSaleStatus(@PathVariable UUID saleId) {
        return salesService.setSaleStatus(saleId, true);
    }

    @PostMapping("/total-price/{saleId}")
    public double calculateTotalPrice(@PathVariable UUID saleId) {
        return salesService.calculateTotalPrice(saleId);
    }
}
