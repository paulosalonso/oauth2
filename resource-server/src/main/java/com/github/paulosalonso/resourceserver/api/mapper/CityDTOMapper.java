package com.github.paulosalonso.resourceserver.api.mapper;

import com.github.paulosalonso.resourceserver.api.dto.CityDTO;
import com.github.paulosalonso.resourceserver.api.dto.CreateCityDTO;
import com.github.paulosalonso.resourceserver.repository.model.City;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CityDTOMapper {
	
	public City toDomainObject(CreateCityDTO cidadeInput) {
		return City.builder()
				.nome(cidadeInput.getNome())
				.estado(cidadeInput.getEstado())
				.build();
	}
	
	public void copyToDomainObject(CreateCityDTO cidadeInput, City cidade) {
		cidade.setNome(cidadeInput.getNome());
		cidade.setEstado(cidadeInput.getEstado());
	}

	public CityDTO toModel(City cidade) {
		return CityDTO.builder()
				.id(cidade.getId())
				.nome(cidade.getNome())
				.estado(cidade.getEstado())
				.build();
	}

	public List<CityDTO> toCollectionModel(List<City> cidades) {
		return cidades.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
}
