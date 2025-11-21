package com.example.nav.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String icon;
    private Integer sortOrder;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @lombok.ToString.Exclude
    private List<Link> links;
}
