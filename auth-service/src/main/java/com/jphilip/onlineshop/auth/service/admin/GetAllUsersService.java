package com.jphilip.onlineshop.auth.service.admin;

import com.jphilip.onlineshop.auth.dto.UserResponseDTO;
import com.jphilip.onlineshop.auth.mapper.UserMapper;
import com.jphilip.onlineshop.auth.repository.UserRepository;
import com.jphilip.onlineshop.auth.service.common.query.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersService implements Query<Void, List<UserResponseDTO>> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDTO> execute(Void input) {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }
}
