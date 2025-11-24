package com.example.nav.controller;

import com.example.nav.entity.Category;
import com.example.nav.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAllByOrderBySortOrderAsc();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        Integer maxSortOrder = categoryRepository.findMaxSortOrder();
        category.setSortOrder(maxSortOrder == null ? 0 : maxSortOrder + 1);
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryDetails.getName());
                    category.setIcon(categoryDetails.getIcon());
                    // sortOrder is handled separately or via reorder
                    return ResponseEntity.ok(categoryRepository.save(category));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/reorder")
    public ResponseEntity<?> reorderCategories(@RequestBody List<Long> sortedIds) {
        for (int i = 0; i < sortedIds.size(); i++) {
            Long id = sortedIds.get(i);
            Category category = categoryRepository.findById(id).orElse(null);
            if (category != null) {
                category.setSortOrder(i);
                categoryRepository.save(category);
            }
        }
        return ResponseEntity.ok().build();
    }
}
