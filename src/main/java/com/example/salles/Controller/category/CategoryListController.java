package com.example.salles.Controller.category;


import com.example.salles.Controller.category.service.CategoryListService;
import com.example.salles.Entity.CategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryListController {
    private final CategoryListService service;

    @Autowired
    public CategoryListController(CategoryListService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoryList> createCategory(@RequestBody CategoryList categoryList) {
        return ResponseEntity.ok(service.create(categoryList));
    }

    @GetMapping
    public ResponseEntity<List<CategoryList>> getAllCategory() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryList> getByIdCategory(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryList> updateCategory(@PathVariable UUID id, @RequestBody CategoryList categoryList) {
        return ResponseEntity.ok(service.updateCategory(id, categoryList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
