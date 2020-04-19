package com.springframework.services;

import com.springframework.commands.IngredientCommand;

/**
 * @author diego
 * @since 08/04/2020
 */
public interface IngredientService {
  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
