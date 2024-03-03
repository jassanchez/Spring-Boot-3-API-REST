package com.asanchez.springboot.apirest.apirestjpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asanchez.springboot.apirest.apirestjpa.entities.Role;
import com.asanchez.springboot.apirest.apirestjpa.entities.User;
import com.asanchez.springboot.apirest.apirestjpa.repositories.IRoleRepository;
import com.asanchez.springboot.apirest.apirestjpa.repositories.IUserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Transactional
    @Override
    public User save(User user) {
        Optional<Role> oRoleUser = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        oRoleUser.ifPresent(role -> roles.add(role));

        if(user.isAdmin()){
            oRoleUser = roleRepository.findByName("ROLE_ADMIN");
            oRoleUser.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setCreated_at(new Date());
        //user.setEnabled(true);

        return userRepository.save(user);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
