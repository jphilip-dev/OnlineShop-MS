package com.jphilip.onlineshop.auth.controller;

import com.jphilip.onlineshop.auth.dto.UserRequestDTO;
import com.jphilip.onlineshop.auth.dto.UserResponseDTO;
import com.jphilip.onlineshop.auth.service.common.command.dto.UpdateUserCommand;
import com.jphilip.onlineshop.auth.service.user.UpdateUserService;
import com.jphilip.onlineshop.auth.validator.groups.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UpdateUserService updateUserService;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @RequestHeader(value = "X-User-Email") String headerEmail,
            @PathVariable Long id,
            @Validated(OnUpdate.class) @RequestBody UserRequestDTO userRequestDTO) {

        var updatedUser = updateUserService.execute(new UpdateUserCommand(headerEmail, id, userRequestDTO));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);

    }
}
