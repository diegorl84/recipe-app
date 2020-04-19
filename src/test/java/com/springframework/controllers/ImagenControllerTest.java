package com.springframework.controllers;

import com.springframework.services.ImageService;
import com.springframework.services.RecipeService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImagenControllerTest {

  @Mock ImageService imageService;

  @Mock RecipeService recipeService;

  ImageController controller;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    controller = new ImageController(imageService, recipeService);
    mockMvc =
        MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(new ControllerExceptionHandler())
            .build();
  }

  @Test
  public void testGetImageNumberFormatException() throws Exception {

    mockMvc
        .perform(get("/recipe/asdf/recipeimage"))
        .andExpect(status().isBadRequest())
        .andExpect(view().name("400error"));
  }
}
