package com.github.paulosalonso.resourceserver.service;

import static java.lang.String.format;

import com.github.paulosalonso.resourceserver.exception.ResourceNotFoundException;
import com.github.paulosalonso.resourceserver.repository.model.City;
import com.github.paulosalonso.resourceserver.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

	private static final String MSG_CIDADE_EM_USO 
		= "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CityRepository cidadeRepository;
	
	@Transactional
	public City salvar(City cidade) {
		return cidadeRepository.save(cidade);
	}
	
	@Transactional
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(cidadeId);
		}
	}
	
	public City findById(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
			.orElseThrow(() -> new ResourceNotFoundException(cidadeId));
	}
	
}
