package br.com.fiap.carbontrace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "TB_REGIAO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegiao;
    private String nome;
    private Double latitude;
    private Double areaKm2;

    @OneToMany
    private List <Ocorrencia> ocorrencias;

    @OneToMany
    private List <ImagemSatelital> imagensSatelitais;

    @ManyToOne
    @JoinColumn(name = "fk_regiao_estado")
    private Estado estado;
}
