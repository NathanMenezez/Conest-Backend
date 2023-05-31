package br.com.conest.api.controller;

import br.com.conest.api.model.dto.LoginDTO;
import br.com.conest.api.model.dto.UsuarioDTO;
import br.com.conest.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return service.cadastrarUsuario(usuarioDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        return service.login(loginDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return service.listar();
    }

}
