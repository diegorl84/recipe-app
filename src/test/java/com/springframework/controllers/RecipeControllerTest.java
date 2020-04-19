package com.springframework.controllers;

import com.springframework.commands.RecipeCommand;
import com.springframework.domain.Recipe;
import com.springframework.exceptions.NotFoundException;
import com.springframework.services.RecipeService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

  @Mock RecipeService recipeService;

  RecipeController controller;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    controller = new RecipeController(recipeService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void showById() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    when(recipeService.findById(anyLong())).thenReturn(recipe);

    mockMvc
        .perform(get("/recipe/1/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"));
  }

  @Test
  public void testPostNewRecipeForm() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(2L);

    when(recipeService.saveRecipeCommand(any())).thenReturn(command);

    mockMvc
        .perform(
            post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/2/show"));
  }

  @Test
  public void testGetRecipe() throws Exception {

    Recipe recipe = new Recipe();
    recipe.setId(1L);

    when(recipeService.findById(anyLong())).thenReturn(recipe);

    mockMvc
        .perform(get("/recipe/1/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"))
        .andExpect(model().attributeExists("recipe"));
  }

  @Test
  public void testGetRecipeNotFound() throws Exception {

    Recipe recipe = new Recipe();
    recipe.setId(1L);

    when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

    mockMvc.perform(get("/recipe/1/show")).andExpect(status().isNotFound());
  }

  @Test
  public void testGetRecipeNumberFormat() throws Exception {

    mockMvc
        .perform(get("/recipe/abc/show"))
        .andExpect(status().isBadRequest())
        .andExpect(view().name("400error"));
  }

  @Test
  public void testDeleteAction() throws Exception {
    mockMvc
        .perform(get("/recipe/1/delete"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/"));

    verify(recipeService, times(1)).deleteById(anyLong());
  }
}
