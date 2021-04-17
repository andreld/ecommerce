package com.andre.ecommerce.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	//Método instancia um bean do tipo ModelMapper que será gerenciado pelo Spring
	@Bean 
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}