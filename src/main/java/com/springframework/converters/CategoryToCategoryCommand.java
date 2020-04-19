package com.springframework.converters;

import com.springframework.commands.CategoryCommand;
import com.springframework.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author diego
 * @since 09/03/2020
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
	@Override
	public CategoryCommand convert(Category category) {
		return null;
	}
}
