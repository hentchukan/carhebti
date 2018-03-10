package prv.carhebti.common.tools;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import prv.carhebti.business.entities.ICarhebtiEntity;

public class ConversionTool {

	public static Integer[] toInteger(String[] items) throws IllegalArgumentException {
		
		if (items == null)
			throw new IllegalArgumentException("List argument should not be null");
		
		List<Integer> converted = new ArrayList<>();
		for (String item : items) {
			try {
				converted.add(Integer.valueOf(item));
			} catch (NumberFormatException nfe) {
				throw new IllegalArgumentException("Number conversion failed "+ item);
			}
		}
		
		return converted.toArray(new Integer[converted.size()]);
	}
	
	public static Integer toInteger(String integer) {
		Integer converted = null;
		if (integer == null || integer.isEmpty())
			return null;
		try {
			converted = new Integer(integer.trim());
		} catch (NumberFormatException nfe) {
			converted = null;
		}
		
		return converted;
	}
	
	public static String toJson(List<? extends ICarhebtiEntity> entities) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    	//Define map which will be converted to JSON
    	//1. Convert List of Person objects to JSON
    	return objectMapper.writeValueAsString(entities);
	}

	public static String toString(Date date) {
		
		if (date == null)
			return "";

		String parsed = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		parsed = sdf.format(date);

		return parsed;
	}
	
	public static Date toDate(String dateString) {
		return toDate(dateString, "dd/MM/yyyy");
	}

	public static Date toDate(String dateString, String pattern) {

		if (dateString == null || dateString.isEmpty())
			return null;

		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			date = null;
		}

		return date;
	}
	
	public static BigDecimal toBigDecimal(String parameter) {
		BigDecimal converted = null;
		if (parameter == null || parameter.isEmpty())
			return null;
		try {
			converted = new BigDecimal(parameter);
		} catch (NumberFormatException nfe) {
			converted = null;
		}
		
		return converted;
	}

	
}
