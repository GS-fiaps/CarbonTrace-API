package br.com.fiap.carbontrace.repositories;

import br.com.fiap.carbontrace.model.Satelite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SateliteRepository extends JpaRepository <Satelite, Long> {
}
