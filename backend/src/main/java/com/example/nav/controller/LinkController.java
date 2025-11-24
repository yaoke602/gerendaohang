package com.example.nav.controller;

import com.example.nav.entity.Category;
import com.example.nav.entity.Link;
import com.example.nav.repository.CategoryRepository;
import com.example.nav.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/links")
@CrossOrigin(origins = "*")
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<Link> createLink(@RequestBody LinkRequest linkRequest) {
        return categoryRepository.findById(linkRequest.getCategoryId())
                .map(category -> {
                    Link link = new Link();
                    link.setTitle(linkRequest.getTitle());
                    link.setDescription(linkRequest.getDescription());
                    link.setUrl(linkRequest.getUrl());
                    link.setIcon(linkRequest.getIcon());
                    link.setCategory(category);
                    
                    Integer maxSortOrder = linkRepository.findMaxSortOrder(category.getId());
                    link.setSortOrder(maxSortOrder == null ? 0 : maxSortOrder + 1);
                    
                    return ResponseEntity.ok(linkRepository.save(link));
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Link> updateLink(@PathVariable Long id, @RequestBody LinkRequest linkRequest) {
        return linkRepository.findById(id)
                .map(link -> {
                    link.setTitle(linkRequest.getTitle());
                    link.setDescription(linkRequest.getDescription());
                    link.setUrl(linkRequest.getUrl());
                    link.setIcon(linkRequest.getIcon());
                    
                    if (linkRequest.getCategoryId() != null) {
                        Category category = categoryRepository.findById(linkRequest.getCategoryId()).orElse(null);
                        if (category != null) {
                            link.setCategory(category);
                        }
                    }
                    
                    return ResponseEntity.ok(linkRepository.save(link));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable Long id) {
        return linkRepository.findById(id)
                .map(link -> {
                    linkRepository.delete(link);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/reorder")
    public ResponseEntity<?> reorderLinks(@RequestBody List<Long> sortedIds) {
        for (int i = 0; i < sortedIds.size(); i++) {
            Long id = sortedIds.get(i);
            Link link = linkRepository.findById(id).orElse(null);
            if (link != null) {
                link.setSortOrder(i);
                linkRepository.save(link);
            }
        }
        return ResponseEntity.ok().build();
    }

    // DTO for Link creation/update
    public static class LinkRequest {
        private String title;
        private String description;
        private String url;
        private String icon;
        private Long categoryId;

        // Getters and Setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    }
}
