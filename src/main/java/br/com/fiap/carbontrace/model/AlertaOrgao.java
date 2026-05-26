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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private AlertaOrgaoId id;

    private LocalDate dataNotificacao;
    @Enumerated
    private StatusNotificacao statusNotificacao;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "fk_ao_alerta ")
    private Alerta alerta;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "fk_ao_orgao")
    private OrgaoAmbiental orgaoAmbiental;
}
