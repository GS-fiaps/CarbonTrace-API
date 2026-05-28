package br.com.fiap.carbontrace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_OCORRENCIA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOcorrencia;
    private LocalDate dataOcorrencia;
    private String descricao;
    private Double areaEstimadaKm2;

    @ManyToOne
    @JoinColumn(name = "id_regiao")
    private Regiao regiao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
