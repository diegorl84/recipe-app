package com.springframework.bootstrap;

import com.springframework.domain.*;
import com.springframework.repositories.CategoryRepository;
import com.springframework.repositories.RecipeRepository;
import com.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author diego
 * @since 07/01/2020
 */
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading bootstrap data...");
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();

        Optional<Category> italianCategoryOption = categoryRepository.findByDescription("Italian");
        if(!italianCategoryOption.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Category italianCategory = italianCategoryOption.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Guacamole Recipe");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Directions to prepare guac");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Guac notes");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);
        guacRecipe.setServings(10);

        guacRecipe.addIngredient(new Ingredient("Ripe avocados",new BigDecimal(2),eachUom));
        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(italianCategory);

        recipes.add(guacRecipe);
        return recipes;
    }
}
