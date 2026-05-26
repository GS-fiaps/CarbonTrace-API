package br.com.fiap.carbontrace.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class AlertaOrgaoId {
    private Long idAlerta;
    private Long idOrgao;
}
