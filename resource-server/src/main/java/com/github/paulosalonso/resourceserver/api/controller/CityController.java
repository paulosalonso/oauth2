package com.github.paulosalonso.resourceserver.api.controller;

import com.github.paulosalonso.resourceserver.api.mapper.CityDTOMapper;
import com.github.paulosalonso.resourceserver.api.dto.CityDTO;
import com.github.paulosalonso.resourceserver.api.dto.CreateCityDTO;
import com.github.paulosalonso.resourceserver.repository.model.City;
import com.github.paulosalonso.resourceserver.repository.CityRepository;
import com.github.paulosalonso.resourceserver.service.CityService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

	@Autowired
	private CityRepository cidadeRepository;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CityDTOMapper cittyMapper;
	
	@GetMapping
	@PreAuthorize("@securityHolder.hasScope('read')")
	public List<CityDTO> listar() {
		return cittyMapper.toCollectionModel(cidadeRepository.findAll());
	}

	@GetMapping("/{cidadeId}")
	@PreAuthorize("@securityHolder.hasScope('read')")
	public CityDTO findById(@PathVariable Long cityId) {
		return cittyMapper.toModel(cityService.findById(cityId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("@securityHolder.hasScope('write')")
	public CityDTO adicionar(@RequestBody @Valid CreateCityDTO createCityDTO) {
		City city = cittyMapper.toDomainObject(createCityDTO);
		city = cityService.salvar(city);
		return cittyMapper.toModel(city);
	}

	@PutMapping("/{cidadeId}")
	@PreAuthorize("@securityHolder.hasScope('write')")
	public CityDTO atualizar(@PathVariable Long cidadeId,
			@RequestBody @Valid CreateCityDTO cidadeInput) {

		City cidadeAtual = cityService.findById(cidadeId);
		cittyMapper.copyToDomainObject(cidadeInput, cidadeAtual);
		cidadeAtual = cityService.salvar(cidadeAtual);
		return cittyMapper.toModel(cidadeAtual);
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("@securityHolder.hasScope('write')")
	public void remover(@PathVariable Long cidadeId) {
		cityService.excluir(cidadeId);
	}
	
}
