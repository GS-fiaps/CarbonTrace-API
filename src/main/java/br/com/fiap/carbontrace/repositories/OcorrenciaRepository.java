package br.com.fiap.carbontrace.repositories;

import br.com.fiap.carbontrace.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia,Long> {
}
