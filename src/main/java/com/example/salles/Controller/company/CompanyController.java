package com.example.salles.Controller.company;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.salles.Controller.company.service.CompanyService;
import com.example.salles.Entity.Company;
import com.example.salles.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/companies", produces = "application/json;charset=UTF-8")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable UUID id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable String name) {
        Company company = companyService.getCompanyByName(name);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllCompanyNames() {
        List<String> companyNames = companyService.getAllCompanyNames();
        return ResponseEntity.ok(companyNames);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        User owner = companyService.getUserById(company.getOwner().getId());
        if (owner == null) {
            throw new NotFoundException("User not found");
        }
        company.setOwner(owner);
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        return ResponseEntity.ok(companyService.getAllCompany());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable UUID id, @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(id, company);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/owner")
    public ResponseEntity<Company> addOwnerToCompany(@PathVariable UUID id, @RequestBody User owner) {
        Company updatedCompany = companyService.addOwnerToCompany(id, owner);
        return ResponseEntity.ok(updatedCompany);
    }

    @PutMapping("/{id}/sellers")
    public ResponseEntity<Company> addSellersToCompany(@PathVariable UUID id, @RequestBody List<User> sellers) {
        Company updatedCompany = companyService.addSellersToCompany(id, sellers);
        return ResponseEntity.ok(updatedCompany);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Company> getCompanyByOwnerId(@PathVariable UUID ownerId) {
        Company company = companyService.getCompanyByOwnerId(ownerId);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/sellers/{sellerId}")
    public ResponseEntity<Company> getCompanyBySellerId(@PathVariable UUID sellerId) {
        Company company = companyService.getCompanyBySellerId(sellerId);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        companyService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
