package com.springframework.repositories;

import com.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author diego
 * @since 07/01/2020
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
