package com.jphilip.onlineshop.auth.service;

import com.jphilip.onlineshop.auth.dto.UserResponseDTO;
import com.jphilip.onlineshop.auth.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserServiceHandler {

    private final UserServiceHelper userServiceHelper;
    private final UserMapper userMapper;

    public UserResponseDTO getUserByEmail(String email){
        var user = userServiceHelper.validateUserByEmail(email);
        return userMapper.toDto(user);
    }

    private UserResponseDTO getUserById(Long id){
        var user = userServiceHelper.validateUserById(id);
        return userMapper.toDto(user);
    }


}
