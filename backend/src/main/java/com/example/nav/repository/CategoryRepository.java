package com.example.nav.repository;

import com.example.nav.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderBySortOrderAsc();

    @Query("SELECT MAX(c.sortOrder) FROM Category c")
    Integer findMaxSortOrder();
}
