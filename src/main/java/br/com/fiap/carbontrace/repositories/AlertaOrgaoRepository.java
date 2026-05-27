package br.com.fiap.carbontrace.repositories;

import br.com.fiap.carbontrace.model.AlertaOrgao;
import br.com.fiap.carbontrace.model.AlertaOrgaoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaOrgaoRepository extends JpaRepository<AlertaOrgao, AlertaOrgaoId> {
}
