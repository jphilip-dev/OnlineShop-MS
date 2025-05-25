package com.jphilip.onlineshop.auth.service.common.command.dto;

import com.jphilip.onlineshop.auth.dto.UserRequestDTO;

public record UpdateUserCommand(String headerEmail, Long id, UserRequestDTO userRequestDTO) {
}
