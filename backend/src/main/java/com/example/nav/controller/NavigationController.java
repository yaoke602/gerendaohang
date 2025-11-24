package com.example.nav.controller;

import com.example.nav.entity.Category;
import com.example.nav.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow frontend to access
public class NavigationController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/nav")
    public List<Category> getNavigationData() {
        return categoryRepository.findAllByOrderBySortOrderAsc();
    }
}
