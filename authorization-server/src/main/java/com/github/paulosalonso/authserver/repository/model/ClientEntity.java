package com.github.paulosalonso.authserver.repository.model;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ClientEntity {

  @Id
  private String clientId;

  private String clientSecret;

  @ElementCollection
  private List<String> resources;

  @ElementCollection
  private List<String> scopes;

  @ElementCollection
  private List<String> grantTypes;

  @ElementCollection
  private List<String> authorities;

  @ElementCollection
  private List<String> redirectUris;
}
