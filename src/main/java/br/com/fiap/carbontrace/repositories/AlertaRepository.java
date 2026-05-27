package br.com.fiap.carbontrace.repositories;

import br.com.fiap.carbontrace.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta,Long> {
}
