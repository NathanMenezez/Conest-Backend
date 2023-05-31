package br.com.conest.api.service;

import br.com.conest.api.model.UsuarioModel;
import br.com.conest.api.model.dto.LoginDTO;
import br.com.conest.api.model.dto.UsuarioDTO;
import br.com.conest.api.repository.UsuarioRepository;
import br.com.conest.api.security.TokenJWT;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public ResponseEntity<?> cadastrarUsuario(UsuarioDTO usuarioDTO) {

        if(repository.existsByCpf(usuarioDTO.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado no sistema!");
        }

        if(repository.existsByEmail(usuarioDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado no sistema!");
        }

        usuarioDTO.setSenha(encoder.encode(usuarioDTO.getSenha()));

        UsuarioModel usuarioModel = new UsuarioModel();

        BeanUtils.copyProperties(usuarioDTO, usuarioModel);

        repository.save(usuarioModel);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioModel);
    }

    public ResponseEntity<?> login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());
        Authentication auth = manager.authenticate(userToken);
        String token = tokenService.gerarToken((UsuarioModel) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenJWT(token));
    }

    public ResponseEntity<?> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
