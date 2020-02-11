package com.springframework.services;

import com.springframework.domain.Recipe;

import java.util.Set;

/**
 * @author diego
 * @since 08/01/2020
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
}
