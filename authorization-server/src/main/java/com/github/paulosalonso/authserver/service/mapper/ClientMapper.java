package com.github.paulosalonso.authserver.service.mapper;

import com.github.paulosalonso.authserver.repository.model.ClientEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public final class ClientMapper {

  private ClientMapper() {}

  public static ClientDetails toClientDetails(ClientEntity client) {
    var clientDetails = new BaseClientDetails(client.getClientId(),
        toCommaSeparatedString(client.getResources()),
        toCommaSeparatedString(client.getScopes()),
        toCommaSeparatedString(client.getGrantTypes()),
        toCommaSeparatedString(client.getAuthorities()),
        toCommaSeparatedString(client.getRedirectUris()));
    clientDetails.setClientSecret(client.getClientSecret());

    return clientDetails;
  }

  private static String toCommaSeparatedString(List<String> values) {
    return values.stream()
        .collect(Collectors.joining(","));
  }

}
