package com.example.salles.Controller.category.service;


import com.amazonaws.services.kms.model.NotFoundException;
import com.example.salles.Entity.CategoryList;
import com.example.salles.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryListService {
    private final CategoryRepo repo;

    @Autowired
    public CategoryListService(CategoryRepo repo) {
        this.repo = repo;
    }

    public CategoryList create(CategoryList category) {
        return repo.save(category);
    }

    public CategoryList getById(UUID id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Category not found with id " + id));
    }

    public List<CategoryList> getAll() {
        return repo.findAll();
    }

    public CategoryList updateCategory(UUID id, CategoryList category) {
        CategoryList categoryList = getById(id);
        categoryList.setName(category.getName());
        return repo.save(categoryList);
    }

    public void deleteById(UUID id) {
        repo.deleteById(id);
    }
}
