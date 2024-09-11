package com.example.demo.service;

import com.example.demo.model.acceso.Role;
import com.example.demo.model.acceso.RoleName;
import com.example.demo.model.acceso.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


    public User save(User user, Set<RoleName> roleNames) {
        for (RoleName roleName : roleNames) {
            Optional<Role> existingRole = roleRepository.findByName(roleName);
            if (existingRole.isPresent()) {
                user.getRoles().add(existingRole.get());
            } else {
                Role newRole = new Role();
                newRole.setName(roleName);
                roleRepository.save(newRole);
                user.getRoles().add(newRole);
            }
        }
        return userRepository.save(user);
    }

    /*
    public User save(User user, Set<Role> roles) {   //acabas de quitar este codigo
        for (Role role : roles) {
            Optional<Role> existingRole = roleRepository.findByName(role.getName());
            if (existingRole.isPresent()) {
                user.getRoles().add(existingRole.get());
            } else {
                roleRepository.save(role);
                user.getRoles().add(role);
            }
        }
        return userRepository.save(user);
    }

     */


    /*
    public void save(User user) {
        userRepository.save(user);
    }

     */

}
