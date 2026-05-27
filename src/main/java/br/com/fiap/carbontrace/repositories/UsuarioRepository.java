package br.com.fiap.carbontrace.repositories;

import br.com.fiap.carbontrace.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
