package com.jphilip.onlineshop.apigateway.dto;

import java.util.List;

public record AuthDetailsResponseDTO(Long id, String email, String name, List<String> roles) {
}
