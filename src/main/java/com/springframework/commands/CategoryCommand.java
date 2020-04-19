package com.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author diego
 * @since 25/02/2020
 */
@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
	private Long id;
	private String description;
}
