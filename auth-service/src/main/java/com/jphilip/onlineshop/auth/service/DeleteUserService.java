package com.jphilip.onlineshop.auth.service;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import com.jphilip.onlineshop.auth.exception.custom.UserOwnershipException;
import com.jphilip.onlineshop.auth.mapper.UserMapper;
import com.jphilip.onlineshop.auth.repository.UserRepository;
import com.jphilip.onlineshop.auth.service.common.command.Command;
import com.jphilip.onlineshop.auth.service.common.command.dto.DeleteUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements Command<DeleteUserCommand, Void> {

    private final UserRepository userRepository;

    private final UserServiceHelper userServiceHelper;
    private final ErrorCodeConfig errorCodeConfig;

    @Override
    public Void execute(DeleteUserCommand deleteUserCommand) {

        // Extract data
        var headerEmail = deleteUserCommand.headerEmail();
        var id = deleteUserCommand.id();

        // Get save user details, this will throw exception if id doesn't exist
        var user = userServiceHelper.validateUserById(id);

        // Check ownership
        userServiceHelper.ownershipCheck(user.getEmail(), headerEmail);

        // Delete user
        userRepository.delete(user);

        // Returning null as required by Command<Long, Void>
        return null;
    }
}
