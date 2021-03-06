package com.springframework.services;

import com.springframework.commands.IngredientCommand;
import com.springframework.converters.IngredientToIngredientCommand;
import com.springframework.domain.Recipe;
import com.springframework.repositories.RecipeRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private final RecipeRepository recipeRepository;

  public IngredientServiceImpl(
      IngredientToIngredientCommand ingredientToIngredientCommand,
      RecipeRepository recipeRepository) {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    if(!recipeOptional.isPresent()){
      log.error("Recipe id not found:" + recipeId);
    }
    Recipe recipe = recipeOptional.get();

    Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
        .findFirst();

    if(!ingredientCommandOptional.isPresent()){
      log.error("Ingredient id not found: " + ingredientId);
    }
    return ingredientCommandOptional.get();
  }
}
