package com.jphilip.onlineshop.auth.service;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import com.jphilip.onlineshop.auth.dto.*;
import com.jphilip.onlineshop.auth.exception.custom.MissingJwtException;
import com.jphilip.onlineshop.auth.exception.custom.UserPasswordMismatchException;
import com.jphilip.onlineshop.auth.service.user.CreateUserService;
import com.jphilip.onlineshop.auth.service.user.UserServiceHelper;
import com.jphilip.onlineshop.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CreateUserService createUserService;
    private final UserServiceHelper userServiceHelper;
    private final ErrorCodeConfig errorCodeConfig;
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public TokenResponseDTO authenticate(LoginRequestDTO loginRequestDTO){

        var user = userServiceHelper.validateUserByEmail(loginRequestDTO.email());

        if(!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())){
            throw new UserPasswordMismatchException(errorCodeConfig.getUnauthorized());
        }

        String token = jwtUtil.generateToken(user);

        return new TokenResponseDTO(token);
    }

    public AuthDetailsResponseDTO validateToken(String token){

        if(token == null || !token.startsWith("Bearer ")){
            throw new MissingJwtException();
        }

        var claims = jwtUtil.validateToken(token.substring(7));

        String email = claims.get("email", String.class);
        Long id = claims.get("id", Long.class);
        String name = claims.get("name", String.class);

        return new AuthDetailsResponseDTO(id,email,name);
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO){
        return createUserService.execute(userRequestDTO);
    }
}
