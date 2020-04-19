package com.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author diego
 * @since 02/03/2020
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
  private Long id;
  private Long recipeId;
  private String description;
  private BigDecimal amount;
  private UnitOfMeasureCommand uom;
}
