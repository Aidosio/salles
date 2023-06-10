package com.example.salles.Controller.company.service;



import com.amazonaws.services.kms.model.NotFoundException;
import com.example.salles.Entity.Company;
import com.example.salles.Entity.User;
import com.example.salles.Repository.CompanyRepo;
import com.example.salles.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepo companyRepository;


    private final UserRepo userRepository;

    public CompanyService(CompanyRepo companyRepository, UserRepo userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public Company getCompanyById(UUID id) {
        return companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company not found with id " + id));
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public Company updateCompany(UUID id, Company company) {
        Company existingCompany = getCompanyById(id);
        existingCompany.setName(company.getName());
        existingCompany.setPurchases(company.getPurchases());
        return companyRepository.save(existingCompany);
    }

    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }

    public Company addOwnerToCompany(UUID id, User owner) {
        Company company = getCompanyById(id);
        company.setOwner(owner);
        return companyRepository.save(company);
    }

    public Company addSellersToCompany(UUID id, List<User> sellers) {
        Company company = getCompanyById(id);
        company.getSellers().addAll(sellers);
        return companyRepository.save(company);
    }

    public Company getCompanyByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Company not found with name " + name));
    }

    public List<String> getAllCompanyNames() {
        return companyRepository.findAll()
                .stream()
                .map(Company::getName)
                .collect(Collectors.toList());
    }

    public Company getCompanyByOwnerId(UUID ownerId) {
        return companyRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new NotFoundException("Company not found with owner id " + ownerId));
    }

    public Company getCompanyBySellerId(UUID sellerId) {
        return companyRepository.findBySellersId(sellerId);
    }

}


