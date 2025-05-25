package com.jphilip.onlineshop.auth.service;

import com.jphilip.onlineshop.auth.dto.UserRequestDTO;
import com.jphilip.onlineshop.auth.dto.UserResponseDTO;
import com.jphilip.onlineshop.auth.entity.Role;
import com.jphilip.onlineshop.auth.mapper.UserMapper;
import com.jphilip.onlineshop.auth.repository.RoleRepository;
import com.jphilip.onlineshop.auth.repository.UserRepository;
import com.jphilip.onlineshop.auth.service.common.command.Command;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements Command<UserRequestDTO, UserResponseDTO> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "USER";


    // DEV purposes, will use data.sql to load data
    @PostConstruct
    private void init() {
        if (roleRepository.findById(USER_ROLE).isEmpty()) {
            var newRole = new Role(USER_ROLE, "NORMAL USER");
            roleRepository.save(newRole);
        }
    }

    @Override
    public UserResponseDTO execute(UserRequestDTO userRequestDTO) {

        // Map dto to entity
        var newUser = userMapper.toEntity(userRequestDTO);

        // encrypt password
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // set to Inactive
        newUser.setIsActive(true); // dev

        // Add role
        Role role = roleRepository.findById(USER_ROLE)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + USER_ROLE));

        newUser.addRole(role);

        // Save
        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }
}
