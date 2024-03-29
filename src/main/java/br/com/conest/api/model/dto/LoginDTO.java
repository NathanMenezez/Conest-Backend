package br.com.conest.api.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
public class LoginDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;
}
