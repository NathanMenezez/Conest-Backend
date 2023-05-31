package br.com.conest.api.repository;

import br.com.conest.api.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    UserDetails findByEmail(String username);
}
