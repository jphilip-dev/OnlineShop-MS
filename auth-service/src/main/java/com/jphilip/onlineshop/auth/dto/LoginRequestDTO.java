package com.jphilip.onlineshop.auth.dto;

import com.jphilip.onlineshop.auth.validator.groups.OnCreate;
import com.jphilip.onlineshop.auth.validator.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @Email
        @NotBlank
        String email,
        @NotBlank
        @Size(min = 6, groups = {OnCreate.class, OnUpdate.class}, message = "ERROR_MINIMUM_LENGTH_SIX_CHARS")
        String password) {
}
