package it.lucatogno.servercasa.messagistica;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        String date = jsonparser.getText();
	        try {
	            return format.parse(date);
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
	}
}
