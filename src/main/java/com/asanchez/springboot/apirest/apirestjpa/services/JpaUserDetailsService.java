package com.asanchez.springboot.apirest.apirestjpa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asanchez.springboot.apirest.apirestjpa.entities.User;
import com.asanchez.springboot.apirest.apirestjpa.repositories.IUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = userRepository.findByUsername(username);
        if (oUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s not exist!", username));
        }

        User user = oUser.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authorities);
    }

}
