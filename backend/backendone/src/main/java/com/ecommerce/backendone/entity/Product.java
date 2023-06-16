package com.ecommerce.backendone.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @SequenceGenerator(name = "productSequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
    private Integer productId;
    private String productName;
    private String imageUrl;
    @Column(length = 1000)
    private String description;
    private Integer markedPrice;
    private Integer sellingPrice;
    private Integer reviewCount;
    private Integer totalRating;
    @ManyToOne
    @JsonBackReference
    private Category category;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Review> reviews;
}
