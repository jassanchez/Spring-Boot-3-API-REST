package com.asanchez.springboot.apirest.apirestjpa.services;

import java.util.List;

import com.asanchez.springboot.apirest.apirestjpa.entities.User;

public interface IUserService {

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    User save(User user);

    boolean existByUsername(String username);
}
