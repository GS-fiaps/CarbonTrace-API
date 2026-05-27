package br.com.fiap.carbontrace.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaOrgaoId {
    private Long idAlerta;
    private Long idOrgao;
}
