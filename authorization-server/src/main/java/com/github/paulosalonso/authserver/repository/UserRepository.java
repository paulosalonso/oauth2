package com.github.paulosalonso.authserver.repository;

import com.github.paulosalonso.authserver.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {}
