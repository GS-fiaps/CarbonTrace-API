package br.com.fiap.carbontrace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "TB_IMAGEM_SATELITAL")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagemSatelital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagem;
    private LocalDate dataCaptura;
    private Double resolucaoMetros;
    private String urlImagem;

    @OneToMany
    private List<Analise> analises;

    @ManyToOne
    @JoinColumn(name = "fk_imagem_regiao")
    private Regiao regiao;

    @ManyToOne
    @JoinColumn(name = "fk_imagem_satelite")
    private Satelite satelite;
}
