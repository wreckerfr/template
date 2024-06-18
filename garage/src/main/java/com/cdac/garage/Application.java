package com.cdac.garage;

import java.time.LocalDate;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
		mapper.addConverter(new StringToDateConverter());
		return mapper;
	}
	
	public class StringToDateConverter extends AbstractConverter<String, LocalDate>{
		
		@Override
		public LocalDate convert(String s) {
			return LocalDate.parse(s);
		}
		
	}
}
