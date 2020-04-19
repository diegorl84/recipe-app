package com.springframework.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.springframework.commands.IngredientCommand;
import com.springframework.commands.RecipeCommand;
import com.springframework.services.IngredientService;
import com.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author diego
 * @since 28/03/2020
 */
public class IngredientControllerTest {

  @Mock IngredientService ingredientService;

  @Mock RecipeService recipeService;

  IngredientController controller;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    controller = new IngredientController(recipeService, ingredientService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    // Given
    RecipeCommand recipeCommand = new RecipeCommand();
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

    // when
    mockMvc
        .perform(get("/recipe/1/ingredients"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/list"))
        .andExpect(model().attributeExists("recipe"));

    // then
    verify(recipeService, times(1)).findCommandById(anyLong());
  }

  @Test
  public void testShowIngredient() throws Exception {
    // Given
    IngredientCommand ingredientCommand = new IngredientCommand();

    // When
    when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

    // then
    mockMvc
        .perform(get("/recipe/1/ingredient/2/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/show"))
        .andExpect(model().attributeExists("ingredient"));
  }
}
