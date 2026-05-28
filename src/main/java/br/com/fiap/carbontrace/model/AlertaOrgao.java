package br.com.fiap.carbontrace.model;

import br.com.fiap.carbontrace.enums.StatusNotificacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_ALERTA_ORGAO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertaOrgao {
    @EmbeddedId
    private AlertaOrgaoId id;

    private LocalDate dataNotificacao;
    @Enumerated(EnumType.STRING)
    private StatusNotificacao statusNotificacao;

    @MapsId("idAlerta")
    @ManyToOne
    @JoinColumn(name = "id_alerta ")
    private Alerta alerta;

    @MapsId("idOrgao")
    @ManyToOne
    @JoinColumn(name = "id_orgao")
    private OrgaoAmbiental orgaoAmbiental;
}
