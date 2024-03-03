package com.asanchez.springboot.apirest.apirestjpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asanchez.springboot.apirest.apirestjpa.entities.Role;


@Repository
public interface IRoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
