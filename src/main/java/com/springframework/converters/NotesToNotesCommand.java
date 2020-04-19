package com.springframework.converters;

import com.springframework.commands.NotesCommand;
import com.springframework.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author diego
 * @since 09/03/2020
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Override
	public NotesCommand convert(Notes notes) {
		return null;
	}
}
