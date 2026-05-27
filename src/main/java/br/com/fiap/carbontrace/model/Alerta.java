package br.com.fiap.carbontrace.model;

import br.com.fiap.carbontrace.enums.NivelCriticidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "TB_ALERTA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlerta;
    private LocalDate dataEmissao;
    @Enumerated(EnumType.STRING)
    private NivelCriticidade nivelCriticidade;
    private String descricao;

    @OneToMany
    private List<AlertaOrgao> alertasOrgaos;

    @ManyToOne
    @JoinColumn(name = "fk_alerta_analise")
    private Analise analise;
}
