package com.github.paulosalonso.authserver.repository;

import com.github.paulosalonso.authserver.repository.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {}
