package com.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author diego
 * @since 07/01/2020
 */
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}