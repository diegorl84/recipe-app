package com.springframework.services;

import com.springframework.commands.RecipeCommand;
import com.springframework.domain.Ingredient;
import com.springframework.domain.Recipe;

import java.util.Set;

/**
 * @author diego
 * @since 08/01/2020
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand findCommandById(Long l);
    Set<Ingredient> findIngredientByRecipe(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long id);
}
