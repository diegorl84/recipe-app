package com.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author diego
 * @since 02/03/2020
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
	private Long id;
	private String recipeNotes;

}
