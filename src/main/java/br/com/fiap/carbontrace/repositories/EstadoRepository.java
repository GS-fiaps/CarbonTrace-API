package br.com.fiap.carbontrace.repositories;

import br.com.fiap.carbontrace.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository <Estado, Long> {
}
