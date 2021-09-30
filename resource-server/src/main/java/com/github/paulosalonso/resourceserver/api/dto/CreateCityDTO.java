package com.github.paulosalonso.resourceserver.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCityDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String estado;
	
}
