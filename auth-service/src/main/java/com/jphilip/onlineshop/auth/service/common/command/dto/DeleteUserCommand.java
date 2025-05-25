package com.jphilip.onlineshop.auth.service.common.command.dto;

public record DeleteUserCommand(String headerEmail, Long id) {
}
