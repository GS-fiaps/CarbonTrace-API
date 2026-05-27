package br.com.fiap.carbontrace.repositories;

import br.com.fiap.carbontrace.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatorioRepository extends JpaRepository<Relatorio,Long> {
}
