package com.jphilip.onlineshop.auth.service.user;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import com.jphilip.onlineshop.auth.entity.User;
import com.jphilip.onlineshop.auth.exception.custom.UserNotFoundException;
import com.jphilip.onlineshop.auth.exception.custom.UserOwnershipException;
import com.jphilip.onlineshop.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceHelper {

    private final UserRepository userRepository;
    private final ErrorCodeConfig errorCodeConfig;

    public User validateUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() ->new UserNotFoundException(
                        errorCodeConfig.getNotFound()
                ));
    }

    public User validateUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException(
                        errorCodeConfig.getNotFound()
                ));
    }

    public void ownershipCheck(String userEmail, String headerEmail){
        if (!userEmail.equals(headerEmail)){
            throw new UserOwnershipException(
                    errorCodeConfig.getUnauthorized()
            );
        }
    }
}
