package com.github.paulosalonso.resourceserver.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CityDTO {

	private Long id;
	private String nome;
	private String estado;
	
}
