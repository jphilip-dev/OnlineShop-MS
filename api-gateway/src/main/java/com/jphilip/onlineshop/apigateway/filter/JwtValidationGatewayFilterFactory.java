package com.jphilip.onlineshop.apigateway.filter;

import com.jphilip.onlineshop.apigateway.config.RoleBasedAccessConfig;
import com.jphilip.onlineshop.apigateway.dto.AuthDetailsResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<RoleBasedAccessConfig> {

    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder,
                                             @Value("${AUTH_SERVICE_URI}") String authServiceUrl) {
        super(RoleBasedAccessConfig.class);
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    @Override
    public GatewayFilter apply(RoleBasedAccessConfig config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClient.get()
                    .uri("/auth/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .bodyToMono(AuthDetailsResponseDTO.class)
                    .flatMap(userDetails -> {
                        List<String> userRoles = userDetails.roles();

                        // Role Check
                        boolean authorized = userRoles.stream()
                                .anyMatch(role -> config.getRoles().contains(role));

                        if (!authorized) {
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return exchange.getResponse().setComplete();
                        }

                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .headers(httpHeaders -> {
                                    httpHeaders.remove("X-User-Id");
                                    httpHeaders.remove("X-User-Email");
                                })
                                .header("X-User-Id", userDetails.id().toString())
                                .header("X-User-Email", userDetails.email())
                                .build();

                        ServerWebExchange mutatedExchange = exchange.mutate()
                                .request(mutatedRequest)
                                .build();

                        return chain.filter(mutatedExchange);
                    })
                    .onErrorResume(error -> {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }
}
