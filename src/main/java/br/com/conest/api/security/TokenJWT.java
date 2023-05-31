package br.com.conest.api.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenJWT {
    private String token;
}
