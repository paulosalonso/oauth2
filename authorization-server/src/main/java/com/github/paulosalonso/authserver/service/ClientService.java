package com.github.paulosalonso.authserver.service;

import com.github.paulosalonso.authserver.repository.ClientRepository;
import com.github.paulosalonso.authserver.service.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Primary
@Service
public class ClientService implements ClientDetailsService {

  private final ClientRepository clientRepository;

  @Transactional
  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    return clientRepository.findById(clientId)
        .map(ClientMapper::toClientDetails)
        .orElseThrow(() -> new ClientRegistrationException("Client not found"));
  }
}
