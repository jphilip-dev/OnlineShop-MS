package com.jphilip.onlineshop.auth.service;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import com.jphilip.onlineshop.auth.dto.UserResponseDTO;
import com.jphilip.onlineshop.auth.exception.custom.UserOwnershipException;
import com.jphilip.onlineshop.auth.mapper.UserMapper;
import com.jphilip.onlineshop.auth.repository.UserRepository;
import com.jphilip.onlineshop.auth.service.common.command.Command;
import com.jphilip.onlineshop.auth.service.common.command.dto.UpdateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements Command<UpdateUserCommand, UserResponseDTO> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserServiceHelper userServiceHelper;
    private final ErrorCodeConfig errorCodeConfig;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO execute(UpdateUserCommand updateUserCommand) {

        // Extract data
        var headerEmail = updateUserCommand.headerEmail();
        var id = updateUserCommand.id();
        var userRequestDTO = updateUserCommand.userRequestDTO();

        // Get save user details, this will throw exception if id doesn't exist
        var user = userServiceHelper.validateUserById(id);

        // Check ownership
        userServiceHelper.ownershipCheck(user.getEmail(), headerEmail);

        // Update fields
        user.setName(userRequestDTO.getName());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        // Save user, not required but for readability
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}