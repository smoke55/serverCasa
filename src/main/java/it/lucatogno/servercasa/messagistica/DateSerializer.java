package it.lucatogno.servercasa.messagistica;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		jgen.writeString(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
		.format(value));
		
	}
}
