package com.example.nav.repository;

import com.example.nav.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query("SELECT MAX(l.sortOrder) FROM Link l WHERE l.category.id = :categoryId")
    Integer findMaxSortOrder(@Param("categoryId") Long categoryId);
}
