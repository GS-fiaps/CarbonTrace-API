package br.com.fiap.carbontrace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "TB_SATELITE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Satelite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSatelite;
    private String nome;
    private String agencia;
    private Double altitudeKm;
    private Integer anoLancamento;

    @OneToMany
    private List<ImagemSatelital> imagemSatelitais;
}
