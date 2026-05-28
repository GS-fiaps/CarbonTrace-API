package br.com.fiap.carbontrace.model;

import br.com.fiap.carbontrace.enums.StatusAlerta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "TB_ANALISE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnalise;
    private LocalDate dataAnalise;
    private Double areaDesmatadaKm2;
    private Double percentualVariacao;
    @Enumerated(EnumType.STRING)
    private StatusAlerta statusAlerta;

    @OneToMany
    private List<Alerta> alertas;

    @ManyToOne
    @JoinColumn(name = "id_imagem")
    private ImagemSatelital imagemSatelital;
}
