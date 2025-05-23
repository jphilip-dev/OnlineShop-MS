package com.jphilip.onlineshop.auth.mapper;

import com.jphilip.onlineshop.auth.dto.UserResponseDTO;
import com.jphilip.onlineshop.auth.entity.Role;
import com.jphilip.onlineshop.auth.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toDto(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .isActive(user.getIsActive())
                .roles(user.getRoles().stream()
                        .map(Role::getName)
                        .toList())
                .build();
    }
}
