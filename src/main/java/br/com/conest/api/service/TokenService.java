package br.com.conest.api.service;

import br.com.conest.api.model.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private String senha = "SENHASUPERSECRETA";

    public String gerarToken(UsuarioModel usuarioModel){
        try {
            var algoritimo = Algorithm.HMAC256(senha);
            return JWT.create()
                    .withIssuer("Conest")
                    .withSubject(usuarioModel.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar o token jwt", e);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritimo = Algorithm.HMAC256(senha);
            return JWT.require(algoritimo)
                    .withIssuer("Conest")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch (JWTVerificationException e){
            throw new RuntimeException("Token Invalido ou Expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
