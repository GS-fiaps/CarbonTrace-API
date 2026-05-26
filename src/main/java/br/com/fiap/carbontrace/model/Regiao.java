package br.com.fiap.carbontrace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Regiao")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegiao;
    private String nome;
    private Double latitude;
    private Double areaKmQuadrado;
    private Estado estado;
    private List <ImagemSatelital> imagensSatelitais;
    private List <Ocorrencia> ocorrencias;
}
